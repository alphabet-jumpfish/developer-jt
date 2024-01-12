package com.jumpfish.developer.porjects.monitors.access;

import com.jumpfish.developer.porjects.monitors.authority.granter.GrantType;
import com.jumpfish.developer.porjects.monitors.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "oauth2Api", name = "oauth2Api", url = "${feign.url.oauth:}")
public interface OAuth2Api {

    String DEFAULT_CLIENT_ID = "developer-monitors";
    String DEFAULT_CLIENT_SECRET = "secret";
    String DEFAULT_SCOPE = "web";

    @PostMapping(value = "/oauth/token")
    AccessToken getToken(
            @RequestParam("grant_type") String type, // password
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("scope") String scope,
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret) throws Exception;

    default AccessToken defaultGetToken(
            @RequestParam("username") String username,
            @RequestParam("password") String password) throws Exception {
        return getToken(GrantType.PASS_WORD, username, password, DEFAULT_SCOPE, DEFAULT_CLIENT_ID, DEFAULT_CLIENT_SECRET);
    }

    @GetMapping(value = "/api/developer/monitors/access-user/getUser")
    Result<AccessTokenUser> getUser(@RequestParam("access_token") String accessToken);

}
