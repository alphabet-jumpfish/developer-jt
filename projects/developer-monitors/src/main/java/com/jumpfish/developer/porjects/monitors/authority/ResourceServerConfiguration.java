package com.jumpfish.developer.porjects.monitors.authority;

import com.jumpfish.developer.porjects.monitors.filter.AuthenticationTokenFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


@Order(10)
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final AuthenticationTokenFilter authenticationTokenFilter;

    public ResourceServerConfiguration(AuthenticationTokenFilter authenticationTokenFilter) {
        this.authenticationTokenFilter = authenticationTokenFilter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin().and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers(
                        // 自定义接口
                        "/developer/monitors/user-manager/login",
                        "/developer/monitors/access-user/**",
                        // 通用接口
                        "/api/**", "/oauth/token", "/oauth/check_token", "/remove/token", "/*")
                .permitAll()
                //.antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        //token验证过滤器
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    }
}
