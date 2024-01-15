package com.jumpfish.developer.porjects.monitors.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jumpfish.developer.porjects.monitors.access.AccessUser;
import com.jumpfish.developer.porjects.monitors.access.CurrentUser;
import com.jumpfish.developer.porjects.monitors.common.result.Result;
import com.jumpfish.developer.porjects.monitors.domain.authority.entity.ProjectRolePO;
import com.jumpfish.developer.porjects.monitors.domain.authority.vo.OperationProjectRoleVO;
import com.jumpfish.developer.porjects.monitors.domain.authority.vo.ProjectRoleVO;
import com.jumpfish.developer.porjects.monitors.layerpage.PageLayer;
import com.jumpfish.developer.porjects.monitors.layerpage.PageLayerAnnotation;
import com.jumpfish.developer.porjects.monitors.layertime.TimeLayer;
import com.jumpfish.developer.porjects.monitors.layertime.TimeLayerAnnotation;
import com.jumpfish.developer.porjects.monitors.service.AuthorityManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@Api(tags = "权限模块服务")
@RestController
@RequestMapping(value = {"/developer/monitors/authority-manager/", "/api/developer/monitors/authority-manager"})
public class AuthorityManagerController {

    private final AuthorityManagerService authorityManagerService;

    public AuthorityManagerController(AuthorityManagerService authorityManagerService) {
        this.authorityManagerService = authorityManagerService;
    }

    @ApiOperation("【查询】角色")
    @GetMapping("selectRoles")
    public Result<Page<ProjectRoleVO>> selectRoles(
            @ApiIgnore @TimeLayerAnnotation TimeLayer timeLayer,
            @ApiIgnore @PageLayerAnnotation PageLayer pageLayer
    ) {
        Result<Page<ProjectRoleVO>> result = authorityManagerService.selectRoles(pageLayer);
        return result;
    }

    @ApiOperation("【添加】角色")
    @PutMapping("addRole")
    public Result<ProjectRoleVO> addRole(
            @ApiIgnore @AccessUser CurrentUser currentUser,
            @RequestBody OperationProjectRoleVO operationProjectRoleVO
    ) {
        Result<ProjectRoleVO> result = authorityManagerService.addRole(currentUser, operationProjectRoleVO);
        return result;
    }

    @ApiOperation("【修改】角色")
    @PutMapping("updateRole")
    public Result<ProjectRoleVO> updateRole(
            @ApiIgnore @AccessUser CurrentUser currentUser,
            @RequestBody OperationProjectRoleVO operationProjectRoleVO
    ) {
        Result<ProjectRoleVO> result = authorityManagerService.updateRole(currentUser, operationProjectRoleVO);
        return result;
    }

    @ApiOperation("【删除】角色")
    @DeleteMapping("deleteRole")
    public Result deleteRole(
            @ApiIgnore @AccessUser CurrentUser currentUser,
            @RequestParam Long id
    ) {
        Result result = authorityManagerService.deleteRole(currentUser, id);
        return result;
    }

    @PutMapping("constructPermission")
    public Result constructPermission() {
        return null;
    }

    @GetMapping("selectPermissions")
    public Result selectPermissions() {
        return null;
    }

}
