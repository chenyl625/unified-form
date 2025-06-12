package com.jfbrother.baseserver.service.impl;

import com.jfbrother.baseserver.common.Constant;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ServiceException;
import com.jfbrother.baseserver.dao.SysConnUserRoleRepository;
import com.jfbrother.baseserver.dao.SysUsersRepository;
import com.jfbrother.baseserver.enums.PathEnum;
import com.jfbrother.baseserver.jpa.*;
import com.jfbrother.baseserver.jwt.JwtUser;
import com.jfbrother.baseserver.model.*;
import com.jfbrother.baseserver.model.other.UserIdsAndRoleIdsModelOther;
import com.jfbrother.baseserver.service.OtherInfoService;
import com.jfbrother.baseserver.service.RedisService;
import com.jfbrother.baseserver.service.SysUsersService;
import com.jfbrother.baseserver.utils.CopyUtils;
import com.jfbrother.baseserver.utils.FileUtils;
import com.jfbrother.baseserver.utils.ListUtils;
import com.jfbrother.baseserver.utils.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class SysUsersServiceImpl extends BaseServiceImpl implements SysUsersService, UserDetailsService {

    @Autowired
    private SysUsersRepository sysUsersRepository;

    @Autowired
    private SysConnUserRoleRepository sysConnUserRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RedisService redisService;

    @Autowired(required = false)
    private OtherInfoService otherInfoService;

    public static final String LIST_GRANTED_AUTHORITY = "listGrantedAuthority_";

    @Value("${system.password.default}")
    private String defaultPassword;

    @Override
    @Transactional
    public SysUsersModel post(SysUsersModel sysUsersModel) {
        if (!StringUtils.isEmpty(sysUsersModel.getId())) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data updating.");
        }
        SysUsers sysUsers = new SysUsers();
        CopyUtils.copyBean(sysUsersModel, sysUsers);
        // 密码需要经过加密
        sysUsers.setPassword(bCryptPasswordEncoder.encode(defaultPassword));
        sysUsersRepository.save(sysUsers);
        return CopyUtils.copyBean(sysUsers, SysUsersModel.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public SysUsersModel put(String id, SysUsersModel sysUsersModel) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }
        SysUsers sysUsers = findUsersById(id);
        CopyUtils.copyBean(sysUsersModel, sysUsers, Constant.UPDATE_USER_IGNORE_FIELDS);
        sysUsersRepository.save(sysUsers);
        return CopyUtils.copyBean(sysUsers, SysUsersModel.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public SysUsersModel patch(String id, SysUsersModel sysUsersModel) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "This method does not support data addition.");
        }
        SysUsers sysUsers = findUsersById(id);
        CopyUtils.copyBeanFilterNull(sysUsersModel, sysUsers, Constant.UPDATE_USER_IGNORE_FIELDS);
        sysUsersRepository.save(sysUsers);
        return CopyUtils.copyBean(sysUsers, SysUsersModel.class);
    }

    @Override
    public SysUsersModel get(String id) {
        SysUsers sysUsers = findUsersById(id);
        return CopyUtils.copyBean(sysUsers, SysUsersModel.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public PageOb<SysUsersModel> get(SysUsersModel search, Pageable pageable) {
        QSysUsers query = QSysUsers.sysUsers;

        Predicate pre = new BooleanBuilder();
        if (!StringUtils.isEmpty(search.getUsername())) {
            pre = ExpressionUtils.and(pre, query.username.like("%" + search.getUsername() + "%"));
        }
        if (!StringUtils.isEmpty(search.getName())) {
            pre = ExpressionUtils.and(pre, query.name.like("%" + search.getName() + "%"));
        }
        Page<SysUsers> pageUsers = sysUsersRepository.findAll(pre, pageable);

        return CopyUtils.copyPageOb(pageUsers, SysUsersModel.class);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public List<SysUsersModel> get(SysUsersModel search, Sort sort) {
        QSysUsers query = QSysUsers.sysUsers;

        Predicate pre = new BooleanBuilder();
        if (!StringUtils.isEmpty(search.getUsername())) {
            pre = ExpressionUtils.and(pre, query.username.like("%" + search.getUsername() + "%"));
        }
        if (!StringUtils.isEmpty(search.getName())) {
            pre = ExpressionUtils.and(pre, query.name.like("%" + search.getName() + "%"));
        }
        if (!StringUtils.isEmpty(search.getDeptId())) {
            pre = ExpressionUtils.and(pre, query.deptId.eq(search.getDeptId()));
        }

        Pageable pageable = PageRequest.of(0, max, sort);
        Page<SysUsers> pageUsers = sysUsersRepository.findAll(pre, pageable);

        return CopyUtils.copyList(pageUsers.getContent(), SysUsersModel.class);
    }

    @Override
    @Transactional
    public void delete(String ids) {
        if (StringUtils.isEmpty(ids)) {
            throw new ServiceException(ResultCode.BAD_REQUEST, "ids is required!");
        }
        sysUsersRepository.deleteBatch(Arrays.asList(ids.split(Constant.REMOVE_IDS_SPLIT)));
    }

    @Override
    @Transactional
    public List<SysRolesModel> putPermit(String userId, List<String> roleIds) {
        //判断用户是否存在
        if (!sysUsersRepository.findById(userId).isPresent()) {
            throw new ServiceException(ResultCode.NOT_FOUND, "No user!");
        }

        //去重
        roleIds = ListUtils.filter(roleIds, t -> (String) t);
        //判断角色id是否都存在
        QSysRoles qSysRoles = QSysRoles.sysRoles;
        long count = queryFactory.selectFrom(qSysRoles).where(qSysRoles.id.in(roleIds)).fetchCount();
        if (count < roleIds.size()) {
            throw new ServiceException(ResultCode.FORBIDDEN, "Contains non-existent role ID!");
        }

        QSysConnUserRole qSysConnUserRole = QSysConnUserRole.sysConnUserRole;
        //先删除该用户原先与角色的关联信息
        queryFactory.delete(qSysConnUserRole).where(qSysConnUserRole.userId.eq(userId)).execute();

        //重新插入关联信息
        List<SysConnUserRole> listSysConnUserRole = new ArrayList<>();
        for (String roleId : roleIds) {
            listSysConnUserRole.add(SysConnUserRole.of(userId, roleId));
        }
        sysConnUserRoleRepository.saveAll(listSysConnUserRole);

        //重新查询用户关联的角色列表并返回
        return getPermit(userId);
    }

    @Override
    public List<SysRolesModel> getPermit(String userId) {
        //判断用户是否存在
        if (!sysUsersRepository.findById(userId).isPresent()) {
            throw new ServiceException(ResultCode.NOT_FOUND, "No user!");
        }

        QSysConnUserRole qSysConnUserRole = QSysConnUserRole.sysConnUserRole;
        QSysRoles qSysRoles = QSysRoles.sysRoles;
        List<SysRoles> listSysRoles = queryFactory.select(qSysRoles)
                .from(qSysConnUserRole)
                .leftJoin(qSysRoles)
                .on(qSysConnUserRole.roleId.eq(qSysRoles.id))
                .where(qSysConnUserRole.userId.eq(userId).and(
                        //不查询关联不到角色表的数据（因为删除角色的时候，没有把对应的用户角色关联信息删除导致）
                        qSysRoles.id.isNotNull()
                ))
                .fetch();
        return CopyUtils.copyList(listSysRoles, SysRolesModel.class);
    }

    @Override
    public List<SysAuthsModel> getAuths(String userId) {
        //判断用户是否存在
        if (!sysUsersRepository.findById(userId).isPresent()) {
            throw new ServiceException(ResultCode.NOT_FOUND, "No user!");
        }


        QSysAuths qSysAuths = QSysAuths.sysAuths;
        List<SysAuths> list = null;
        if ("admin".equals(userId)) {
            //如果是管理员，则返回所有权限
            list = queryFactory.selectFrom(qSysAuths).distinct().fetch();
        } else {
            //如果是普通用户，则查询应有的权限
            QSysUsers qSysUsers = QSysUsers.sysUsers;
            QSysConnUserRole qSysConnUserRole = QSysConnUserRole.sysConnUserRole;
            QSysConnRoleAuths qSysConnRoleAuths = QSysConnRoleAuths.sysConnRoleAuths;

            list = queryFactory.select(qSysAuths)
                    .from(qSysUsers)
                    .leftJoin(qSysConnUserRole)
                    .on(qSysUsers.id.eq(qSysConnUserRole.userId))
                    .leftJoin(qSysConnRoleAuths)
                    .on(qSysConnUserRole.roleId.eq(qSysConnRoleAuths.roleId))
                    .leftJoin(qSysAuths)
                    .on(qSysConnRoleAuths.authId.eq(qSysAuths.id))
                    .where(qSysUsers.id.eq(userId).and(qSysAuths.id.isNotNull()))
                    .distinct()
                    .fetch();
        }


        return CopyUtils.copyList(list, SysAuthsModel.class);
    }

    @Override
    public SysUsersModel putPortrait(String id, MultipartFile portrait) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "ID is necessary.");
        }

        String name;
        try {
            name = FileUtils.save(PathEnum.USER_PORTRAIT, portrait).getName();
        } catch (IOException e) {
            throw new ServiceException(ResultCode.INTERNAL_SERVER_ERROR, "头像文件存储到服务器失败。");
        }

        SysUsers sysUsers = findUsersById(id);
        sysUsers.setPortrait(name);
        sysUsersRepository.save(sysUsers);
        return CopyUtils.copyBean(sysUsers, SysUsersModel.class);
    }

    @Override
    public File getPortrait(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException(ResultCode.FORBIDDEN, "ID is necessary.");
        }

        SysUsers sysUsers = findUsersById(id);
        File portraitFile;

        try {
            portraitFile = FileUtils.get(PathEnum.USER_PORTRAIT, sysUsers.getPortrait());
        } catch (ServiceException e) {
            throw new ServiceException(ResultCode.NOT_FOUND, "Not found portrait.");
        } catch (NullPointerException e) {
            throw new ServiceException(ResultCode.NOT_FOUND, "Not found portrait.");
        }

        FileUtils.downFile(portraitFile);
        return portraitFile;
    }

    @Override
    public void changePassword(PasswordModel passwordModel) {
        SysUsers sysUsers;
        try {
            //获取当前登录人
            JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
            //根据当前登录人id获取最新的用户信息
            sysUsers = findUsersById(jwtUser.getId());


        } catch (Exception e) {
            throw new ServiceException(ResultCode.INTERNAL_SERVER_ERROR, "系统错误！");
        }

        if (sysUsers != null
                && sysUsers.getPassword() != null
                && bCryptPasswordEncoder.matches(passwordModel.getNowPassword(), sysUsers.getPassword())) {
            //如果密码正确，则进行密码修改
            sysUsers.setPassword(bCryptPasswordEncoder.encode(passwordModel.getNewPassword()));
            sysUsersRepository.save(sysUsers);
        } else {
            throw new ServiceException(ResultCode.NOT_ACCEPTABLE, "当前密码错误！");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SysCompanyDatabaseModel getDatabase(String userId) {
        QSysCompanyDatabase qSysCompanyDatabase = QSysCompanyDatabase.sysCompanyDatabase;
        QSysUsers qSysUsers = QSysUsers.sysUsers;

        SysCompanyDatabase database = queryFactory.selectFrom(qSysCompanyDatabase)
                .leftJoin(qSysUsers)
                .on(qSysCompanyDatabase.companyId.eq(qSysUsers.companyId))
                .where(qSysUsers.id.eq(userId))
                .fetchOne();
        return CopyUtils.copyBean(database, SysCompanyDatabaseModel.class);
    }

    @Override
    public SysUsersModel getByUserName(String username) {
        QSysUsers qSysUsers = QSysUsers.sysUsers;
        SysUsers sysUsers = queryFactory.selectFrom(qSysUsers)
                .where(qSysUsers.username.eq(username))
                .fetchFirst();
        if (sysUsers == null) {
            throw new ServiceException(ResultCode.NOT_FOUND, "No user!");
        }
        return CopyUtils.copyBean(sysUsers, SysUsersModel.class);
    }

    @Override
    public void putPermit(UserIdsAndRoleIdsModelOther model) {
        List<String> userIds = model.getUserIds();
        List<String> roleIds = model.getRoleIds();

        //去重
        userIds = ListUtils.filter(userIds, t -> (String) t);

        for (String userId : userIds) {
            putPermit(userId, roleIds);
        }
    }


    @Override
    public void saveInfoToRedis(String userId, UserInfoInRedisModel model) {
        log.trace(model.toString());
        //保存1小时
        redisService.set(LIST_GRANTED_AUTHORITY + userId, model, 60 * 60L);
    }

    @Override
    public void cleanInfoToRedis(String userId) {
        if (userId != null && !"".equals(userId)) {
            redisService.remove(LIST_GRANTED_AUTHORITY + userId);
        }
    }

    @Override
    public UserInfoInRedisModel getPermitsFromRedis(String userId) {
        Object obj = redisService.get(LIST_GRANTED_AUTHORITY + userId);
        if (obj == null) {
            //如果信息过期了，则重新查询
            SysUsersModel sysUsersModel = get(userId);
            JwtUser jwtUser = loadUserByUsername(sysUsersModel.getUsername());
            UserInfoInRedisModel model = new UserInfoInRedisModel();
            model.setId(jwtUser.getId());
            model.setName(jwtUser.getName());
            model.setUsername(jwtUser.getUsername());
            model.setPortrait(sysUsersModel.getPortrait());
            model.setCompanyId(sysUsersModel.getCompanyId());
            model.setGlobalRoaming(sysUsersModel.getGlobalRoaming());
            model.setTelephone(sysUsersModel.getTelephone());
            model.setAge(sysUsersModel.getAge());
            model.setDeptId(sysUsersModel.getDeptId());
            model.setUnionId(sysUsersModel.getUnionId());
            model.setOpenId(sysUsersModel.getOpenId());

            //设置权限
            model.setPermits(jwtUser.getAuthoritiesToString());
//
            //当有otherInfoService实现类时，添加其他信息
            if (otherInfoService != null) {
                model.setOtherInfo(otherInfoService.getInfoByUserId(userId));
            }
            saveInfoToRedis(userId, model);
            return model;
        }
        return (UserInfoInRedisModel) obj;
    }

    @Override
    public SysUsersModel getByOpenId(String openId) {
        QSysUsers qSysUsers = QSysUsers.sysUsers;
        List<SysUsers> fetch = queryFactory.selectFrom(qSysUsers).where(qSysUsers.openId.eq(openId)).fetch();
        return fetch.size()>0?CopyUtils.copyBean(fetch.get(0),SysUsersModel.class):new SysUsersModel();
    }


    /**
     * 根据用户id获取用户
     *
     * @param id
     * @return
     */
    private SysUsers findUsersById(String id) {
        SysUsers sysUsers = null;
        try {
            sysUsers = sysUsersRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            // throw new ServiceException(ResultCode.NOT_FOUND, e.getMessage());
            throw new ServiceException(ResultCode.NOT_FOUND, "No sysUsers found.");
        }
        return sysUsers;
    }

    @Override
    public JwtUser loadUserByUsername(String username) {
        SysUsers user = sysUsersRepository.findUserByUsername(username);
        if (user == null) {
            throw new ServiceException(ResultCode.FORBIDDEN, "User name does not exist.");
        }
        return new JwtUser(user);
    }
}
