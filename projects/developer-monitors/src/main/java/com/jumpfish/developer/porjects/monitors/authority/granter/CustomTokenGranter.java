package com.jumpfish.developer.porjects.monitors.authority.granter;

import com.jumpfish.developer.porjects.monitors.authority.domain.user.OauthUserDTO;
import com.jumpfish.developer.porjects.monitors.service.CustomTokenService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import java.util.Map;


public class CustomTokenGranter extends AbstractCustomTokenGranter {

    private final CustomTokenService customTokenService;

    public CustomTokenGranter(
            CustomTokenService customTokenService,
            AuthorizationServerTokenServices tokenServices,
            ClientDetailsService clientDetailsService,
            OAuth2RequestFactory requestFactory
    ) {
        super(tokenServices, clientDetailsService, requestFactory, GrantType.PASS_WORD);
        this.customTokenService = customTokenService;
    }

    @Override
    protected OauthUserDTO getCustomUser(Map<String, String> parameters) {
        String username = parameters.get("username");
        // getUser
        // convert user
        OauthUserDTO user = customTokenService.convertUser(username);
        return user;
    }

}
