package com.jumpfish.developer.porjects.monitors.controller;

import com.jumpfish.developer.porjects.monitors.access.AccessUser;
import com.jumpfish.developer.porjects.monitors.access.CurrentUser;
import com.jumpfish.developer.porjects.monitors.common.result.Result;
import com.jumpfish.developer.porjects.monitors.domain.authority.vo.LoginDetailVO;
import com.jumpfish.developer.porjects.monitors.domain.user.entity.UserPO;
import com.jumpfish.developer.porjects.monitors.layerpage.PageLayer;
import com.jumpfish.developer.porjects.monitors.layerpage.PageLayerAnnotation;
import com.jumpfish.developer.porjects.monitors.layertime.TimeLayer;
import com.jumpfish.developer.porjects.monitors.layertime.TimeLayerAnnotation;
import com.jumpfish.developer.porjects.monitors.service.UserManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@Api(tags = "用户模块服务")
@RestController
@RequestMapping(value = {"/developer/monitors/user-manager/", "/api/developer/monitors/user-manager"})
public class UserManagerController {

    private final UserManagerService userManagerService;

    public UserManagerController(UserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    @ApiOperation("【登陆】用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "13675831750", required = true),
            @ApiImplicitParam(name = "password", value = "密码【1234】", defaultValue = "1234", required = true),
    })
    @GetMapping("login")
    public Result<LoginDetailVO> login(
            @ApiIgnore @TimeLayerAnnotation TimeLayer timeLayer,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        Result<LoginDetailVO> result = userManagerService.login(username, password);
        return result;
    }

    // register user


    @ApiOperation("用户集合应用")
    @GetMapping("users")
    public Result users(
            @ApiIgnore @AccessUser CurrentUser currentUser,
            @ApiIgnore @PageLayerAnnotation PageLayer pageLayer
    ) {
        Result<UserPO> result = userManagerService.users();
        return result;
    }

}
