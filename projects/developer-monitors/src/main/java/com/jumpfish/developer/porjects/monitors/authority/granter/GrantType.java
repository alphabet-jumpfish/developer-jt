package com.jumpfish.developer.porjects.monitors.authority.granter;

import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
public class GrantType {

    /**
     * 密码模式
     */
    public static final String PASS_WORD = "password";

    /**
     * 刷新token
     */
    public static final String REFRESH_TOKEN = "refresh_token";

    /**
     * 客户端模式
     */
    public static final String CLIENT_CREDENTIALS = "client_credentials";

    /**
     * 简化模式
     */
    public static final String IMPLICIT = "implicit";

    /**
     * 授权吗模式
     */
    public static final String AUTHORIZATION_CODE = "authorization_code";

    /**
     * 微信授权方式
     */
    public static final String WECHAT = "wechat";


}
