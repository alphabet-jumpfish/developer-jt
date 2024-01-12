package com.jumpfish.developer.porjects.monitors.authority.granter;


import com.jumpfish.developer.porjects.monitors.authority.domain.user.OauthUserDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractCustomTokenGranter extends AbstractTokenGranter {

    protected AbstractCustomTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap(tokenRequest.getRequestParameters());
        OauthUserDTO customUser = getCustomUser(parameters);
        if (customUser == null) {
            throw new InvalidGrantException("无法获取用户信息");
        }
        Authentication userAuth = new UsernamePasswordAuthenticationToken(customUser, client, customUser.getAuthorities());
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        OAuth2Authentication authentication = new OAuth2Authentication(storedOAuth2Request, userAuth);
        authentication.setDetails(customUser);
        authentication.setAuthenticated(true);
        return authentication;
    }

    protected abstract OauthUserDTO getCustomUser(Map<String, String> parameters);

}
