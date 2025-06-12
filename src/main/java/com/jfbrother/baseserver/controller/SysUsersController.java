package com.jfbrother.baseserver.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jfbrother.baseserver.core.PageOb;
import com.jfbrother.baseserver.core.Result;
import com.jfbrother.baseserver.core.ResultCode;
import com.jfbrother.baseserver.core.ResultGenerator;
import com.jfbrother.baseserver.model.PasswordModel;
import com.jfbrother.baseserver.model.SysAuthsModel;
import com.jfbrother.baseserver.model.SysRolesModel;
import com.jfbrother.baseserver.model.SysUsersModel;
import com.jfbrother.baseserver.model.other.UserIdsAndRoleIdsModelOther;
import com.jfbrother.baseserver.service.SysUsersService;
import com.jfbrother.baseserver.version.ApiVersion;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = {"用户接口-1.0.0-20181025"})
@ApiSort(10)
@Validated
@RestController("users")
@RequestMapping("/api/v1/sysUsers")
public class SysUsersController {

    @Autowired
    private SysUsersService sysUsersService;

    @ApiOperation(value = "用户列表获取", notes = "用户列表获取接口", position = 1)
    @ApiVersion(1)
    @GetMapping
    @RolesAllowed({"users:list"})
    public Result<PageOb<SysUsersModel>> get(
            @PageableDefault(page = 0, size = 10, sort = "createTime", direction = Direction.ASC)
                    Pageable pageable, SysUsersModel search) {
        PageOb<SysUsersModel> pageUserModel = sysUsersService.get(search, pageable);
        return ResultGenerator.genSuccessResult(pageUserModel);
    }

    @ApiOperation(value = "用户列表获取（不分页）", notes = "用户列表获取接口", position = 2)
    @ApiVersion(1)
    @GetMapping("all")
    @RolesAllowed({"users:listAll"})
    public Result<List<SysUsersModel>> get(
            @SortDefault(sort = "createTime", direction = Direction.ASC)
                    Sort sort, SysUsersModel search) {
        List<SysUsersModel> listUserModel = sysUsersService.get(search, sort);
        return ResultGenerator.genSuccessResult(listUserModel);
    }

    @ApiOperation(value = "用户获取", notes = "用户获取接口", position = 3)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}")
    @RolesAllowed({"users:get"})
    public Result<SysUsersModel> get(@PathVariable String id) {
        SysUsersModel userModel;
        userModel = sysUsersService.get(id);
        return ResultGenerator.genSuccessResult(userModel);
    }

    @ApiOperation(value = "用户添加", notes = "用户添加接口", position = 4)
    @ApiVersion(1)
    @PostMapping
    @RolesAllowed({"users:post"})
    public Result<SysUsersModel> post(@RequestBody @Valid SysUsersModel sysUsersModel) {
        sysUsersModel = sysUsersService.post(sysUsersModel);
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, sysUsersModel);
    }

    @ApiOperation(value = "用户全更新", notes = "用户全更新接口", position = 5)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的用户id", required = true, dataType = "string")
    })
    @PutMapping(value = "/{id}")
    @RolesAllowed({"users:put"})
    public Result<SysUsersModel> put(@PathVariable String id, @RequestBody @Valid SysUsersModel sysUsersModel) {
        sysUsersModel = sysUsersService.put(id, sysUsersModel);
        return ResultGenerator.genSuccessResult(ResultCode.OK, sysUsersModel);
    }

    @ApiOperation(value = "用户部分更新", notes = "用户部分更新接口", position = 5)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的用户id", required = true, dataType = "string")
    })
    @PatchMapping(value = "/{id}")
    @RolesAllowed({"users:patch"})
    public Result<SysUsersModel> patch(@PathVariable String id, @RequestBody SysUsersModel sysUsersModel) {
        sysUsersModel = sysUsersService.patch(id, sysUsersModel);
        return ResultGenerator.genSuccessResult(ResultCode.OK, sysUsersModel);
    }

    @ApiOperation(value = "用户删除", notes = "用户删除接口", position = 6)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "需要删除的id列表", required = true, dataType = "string")
    })
    @DeleteMapping
    @RolesAllowed({"users:delete"})
    public Result delete(@RequestParam
                             @NotBlank(message = "用户id不能为空")
                                     String ids) {
        sysUsersService.delete(ids);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }


    @ApiOperation(value = "头像上传", notes = "头像上传接口", position = 7)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "portrait", value = "头像文件", required = true, dataType = "__File")
    })
    @PostMapping("/{id}/portrait")
    public Result<SysUsersModel> postPortrait(@PathVariable String id,@RequestParam MultipartFile portrait) {
        return ResultGenerator.genSuccessResult(ResultCode.CREATED, sysUsersService.putPortrait(id,portrait));
    }

    @ApiOperation(value = "头像下载", notes = "头像下载接口", position = 8)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "string")
    })
    @GetMapping(value = "/{id}/portrait", produces = "application/octet-stream")
    public void postPortrait(@PathVariable String id) {
        sysUsersService.getPortrait(id);
    }

    @ApiOperation(value = "用户授权", notes = "用户授权接口", position = 9)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要授权的用户id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "roleIds", value = "角色id列表", required = true, dataType = "string", allowMultiple = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No User!"),
            @ApiResponse(code = 403, message = "Contains non-existent role ID!")
    })
    @PutMapping(value = "/{id}/roles")
    @RolesAllowed({"users:putRoles"})
    public Result<List<SysRolesModel>> putRoles(@PathVariable String id,
                                                @RequestBody
                                                @NotNull(message = "角色id列表不能为空")
                                                        List<String> roleIds) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, sysUsersService.putPermit(id, roleIds));
    }

    @ApiOperation(value = "用户授权", notes = "用户授权接口", position = 9)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要授权的用户id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "roleIds", value = "角色id列表", required = true, dataType = "string", allowMultiple = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No User!"),
            @ApiResponse(code = 403, message = "Contains non-existent role ID!")
    })
    @PutMapping(value = "/batchRoles")
    @RolesAllowed({"users:putRoles"})
    public Result putRoles(@RequestBody UserIdsAndRoleIdsModelOther model) {
        sysUsersService.putPermit(model);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT );
    }

    @ApiOperation(value = "系统用户角色列表获取", notes = "系统用户角色列表获取接口", position = 10)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "string")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No User!")
    })
    @GetMapping(value = "/{id}/roles")
    @RolesAllowed({"users:getRoles"})
    public Result<List<SysRolesModel>> getRoles(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, sysUsersService.getPermit(id));
    }

    @ApiOperation(value = "系统用户权限列表获取", notes = "系统用户权限列表获取接口", position = 11)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "string")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No User!")
    })
    @GetMapping(value = "/{id}/auths")
    @RolesAllowed({"users:getAuths"})
    public Result<List<SysAuthsModel>> getAuths(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(ResultCode.OK, sysUsersService.getAuths(id));
    }

    @ApiOperation(value = "密码修改（只能修改当前登录人的密码）", notes = "密码修改接口", position = 12)
    @ApiVersion(1)
    @PatchMapping("/change/password")
    public Result<List<SysAuthsModel>> changePassword(@RequestBody @Valid PasswordModel passwordModel) {
        sysUsersService.changePassword(passwordModel);
        return ResultGenerator.genSuccessResult(ResultCode.NO_CONTENT);
    }

    @ApiOperation(value = "用户获取", notes = "用户获取接口", position = 3)
    @ApiVersion(1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string")
    })
    @GetMapping(value = "/{username}/username")
    @RolesAllowed({"users:get"})
    public Result<SysUsersModel> getByUserName(@PathVariable String username) {
        SysUsersModel userModel = sysUsersService.getByUserName(username);
        return ResultGenerator.genSuccessResult(userModel);
    }
}
