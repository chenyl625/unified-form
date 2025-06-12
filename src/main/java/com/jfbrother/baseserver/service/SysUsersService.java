package com.jfbrother.baseserver.service;

import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.model.*;
import com.jfbrother.baseserver.model.other.UserIdsAndRoleIdsModelOther;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface SysUsersService extends BaseService {
    /**
     * 用户添加
     *
     * @param userModel 数据
     * @return
     */
    SysUsersModel post(SysUsersModel userModel);

    /**
     * 用户更新
     *
     * @param id        主键id
     * @param userModel 数据
     * @return
     */
    SysUsersModel put(String id, SysUsersModel userModel);

    /**
     * 用户部分更新
     *
     * @param id            主键id
     * @param sysUsersModel 数据
     * @return
     */
    SysUsersModel patch(String id, SysUsersModel sysUsersModel);

    /**
     * 根据主键id获取数据
     *
     * @param id 主键id
     * @return
     */
    SysUsersModel get(String id);

    /**
     * 根据查询条件和分页条件（包括排序条件），查询数据
     *
     * @param search   查询条件
     * @param pageable 分页条件
     * @return
     */
    PageOb<SysUsersModel> get(SysUsersModel search, Pageable pageable);

    /**
     * 根据查询条件和排序条件，查询数据
     *
     * @param search 查询条件
     * @param sort   分页条件
     * @return
     */
    List<SysUsersModel> get(SysUsersModel search, Sort sort);

    /**
     * 根据ids删除数据
     *
     * @param ids id字符串（多个id通过英文逗号进行分割）
     */
    void delete(String ids);

    /**
     * 对某个用户赋予角色（授权）
     *
     * @param userId  用户id
     * @param roleIds 角色id列表
     * @return
     */
    List<SysRolesModel> putPermit(String userId, List<String> roleIds);

    /**
     * 获取某个用户赋予的角色列表
     *
     * @param userId 用户id
     * @return
     */
    List<SysRolesModel> getPermit(String userId);

    /**
     * 获取某个用户的权限列表
     *
     * @param userId 用户id
     * @return
     */
    List<SysAuthsModel> getAuths(String userId);

    /**
     * 用户头像更新
     *
     * @param id       主键id
     * @param portrait 头像文件
     * @return
     */
    SysUsersModel putPortrait(String id, MultipartFile portrait);

    /**
     * 用户头像获取
     *
     * @param id 主键id
     * @return
     */
    File getPortrait(String id);

    void changePassword(PasswordModel passwordModel);

    /**
     * 获取用户所在公司的数据源
     *
     * @return
     */
    SysCompanyDatabaseModel getDatabase(String userId);

    /**
     * 根据用户名获取数据
     *
     * @param username 用户名
     * @return
     */
    SysUsersModel getByUserName(String username);

    /**
     * 批量赋权
     *
     * @param model
     */
    void putPermit(UserIdsAndRoleIdsModelOther model);

    /**
     * 保存权限列表到redis
     *
     * @param userId
     * @param userInfoInRedisModel
     */
    void saveInfoToRedis(String userId, UserInfoInRedisModel userInfoInRedisModel);

    /**
     * 清空用户在redis中的缓存信息
     *
     * @param userId
     */
    void cleanInfoToRedis(String userId);

    /**
     * 根据用户名从redis获取权限
     *
     * @param userId
     * @return
     */
    UserInfoInRedisModel getPermitsFromRedis(String userId);

    SysUsersModel getByOpenId(String openId);
}
