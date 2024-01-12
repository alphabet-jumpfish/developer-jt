package com.jumpfish.developer.porjects.monitors.authority;

import com.jumpfish.developer.porjects.monitors.authority.exception.UserOAuth2WebResponseExceptionTranslator;
import com.jumpfish.developer.porjects.monitors.authority.granter.CustomTokenGranter;
import com.jumpfish.developer.porjects.monitors.service.CustomTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * 用于校验密码的用户服务
     */
    private final UserDetailsService userServiceDetail;
    private final UserOAuth2WebResponseExceptionTranslator userOAuth2WebResponseExceptionTranslator;
    private final CustomTokenService customTokenService;
    private AuthenticationManager authenticationManager;
    private RedisConnectionFactory redisConnectionFactory;

    public AuthorizationServerConfiguration(
            UserDetailsService userServiceDetail, UserOAuth2WebResponseExceptionTranslator userOAuth2WebResponseExceptionTranslator,
            CustomTokenService customTokenService, RedisConnectionFactory redisConnectionFactory, AuthenticationManager authenticationManager) {
        this.userServiceDetail = userServiceDetail;
        this.userOAuth2WebResponseExceptionTranslator = userOAuth2WebResponseExceptionTranslator;
        this.customTokenService = customTokenService;
        this.redisConnectionFactory = redisConnectionFactory;
        this.authenticationManager = authenticationManager;
    }

    /**
     * 配置ClientID，secret，认证方式等
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String client = "developer-monitors";
        String secret = "secret";

        int tokenTime = 24 * 60 * 60;
        clients.inMemory()
                .withClient(client)
                .secret(encoder.encode(secret))
                .authorizedGrantTypes("password", "authorization_code", "client_credentials", "refresh_token")
                .accessTokenValiditySeconds(tokenTime)
                .refreshTokenValiditySeconds(tokenTime)
                .scopes("web")
        ;

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        List<TokenGranter> tokenGranters =
                authorizationGrantType(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());
        tokenGranters.add(endpoints.getTokenGranter());

        endpoints.tokenGranter(new CompositeTokenGranter(tokenGranters));

        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .userDetailsService(userServiceDetail)
                .reuseRefreshTokens(false)
                .exceptionTranslator(userOAuth2WebResponseExceptionTranslator);
    }

    /**
     * 自定义 授权方式
     *
     * @return
     */
    List<TokenGranter> authorizationGrantType(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        return new ArrayList<>(Arrays.asList(
                new CustomTokenGranter(customTokenService, tokenServices,
                        clientDetailsService,
                        requestFactory)
        ));

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /* 配置token获取合验证时的策略 */
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        return tokenStore;
    }

}
