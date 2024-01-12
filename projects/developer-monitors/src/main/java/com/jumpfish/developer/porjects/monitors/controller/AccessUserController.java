package com.jumpfish.developer.porjects.monitors.controller;

import com.jumpfish.developer.porjects.monitors.access.AccessToken;
import com.jumpfish.developer.porjects.monitors.access.AccessTokenUser;
import com.jumpfish.developer.porjects.monitors.access.OAuth2Api;
import com.jumpfish.developer.porjects.monitors.authority.domain.user.OauthUserDTO;
import com.jumpfish.developer.porjects.monitors.common.result.Result;
import com.jumpfish.developer.porjects.monitors.common.result.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "认证模块服务")
@RestController
@RequestMapping(value = {"/developer/monitors/access-user/", "/api/developer/monitors//access-user"})
public class AccessUserController {

    private final OAuth2Api oAuth2Api;

    public AccessUserController(OAuth2Api oAuth2Api) {
        this.oAuth2Api = oAuth2Api;
    }

    @ApiOperation("默认获取Token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "13675831750", required = true),
            @ApiImplicitParam(name = "password", value = "密码【1234】", defaultValue = "UH94gWdCgfg=", required = true),
    })
    @GetMapping("/token")
    public Result token(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        AccessToken accessToken = null;
        try {
            accessToken = oAuth2Api.defaultGetToken(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultFactory.successOf(accessToken);
    }

    @ApiOperation("根据令牌（token）获取用户信息")
    @GetMapping("/getUser")
    public Result<AccessTokenUser> getUser(
            OAuth2Authentication oAuth2Authentication
    ) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                (UsernamePasswordAuthenticationToken) oAuth2Authentication.getUserAuthentication();
        OauthUserDTO principal = (OauthUserDTO) usernamePasswordAuthenticationToken.getPrincipal();
        AccessTokenUser accessTokenUser = new AccessTokenUser();
        accessTokenUser.setUserId(principal.getUserId());
        accessTokenUser.setUsername(principal.getUsername());
        accessTokenUser.setLabelCode(principal.getLabelCode());
        return ResultFactory.successOf(accessTokenUser);
    }


}
