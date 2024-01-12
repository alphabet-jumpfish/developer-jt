package com.jumpfish.developer.porjects.monitors.filter;

import com.alibaba.fastjson.JSONObject;
import com.jumpfish.developer.porjects.monitors.access.AccessTokenUser;
import com.jumpfish.developer.porjects.monitors.access.OAuth2Api;
import com.jumpfish.developer.porjects.monitors.authority.domain.user.OauthUserDTO;
import com.jumpfish.developer.porjects.monitors.common.result.DefaultResultCode;
import com.jumpfish.developer.porjects.monitors.common.result.Result;
import com.jumpfish.developer.porjects.monitors.common.result.ResultFactory;
import com.jumpfish.developer.porjects.monitors.service.CustomTokenService;
import com.jumpfish.developer.porjects.monitors.utils.ip.IpUtil;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final OAuth2Api oAuth2Api;
    private final CustomTokenService customTokenService;

    public AuthenticationTokenFilter(OAuth2Api oAuth2Api, CustomTokenService customTokenService) {
        this.oAuth2Api = oAuth2Api;
        this.customTokenService = customTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1、获取请求头携带的token
        final String uri = request.getRequestURI();
        final String method = request.getMethod().toLowerCase();
        final String token = request.getHeader("access_token");

        log.info("\n" + "****************【认证处理】**************** \n" +
                        "****************【请求信息】**************** 头部票据信息:{} \n" +
                        "****************【请求信息】**************** 访问URL:{} \n" +
                        "****************【请求信息】**************** 请求方式:{} \n"
                , token, uri, method);

        if (!StringUtils.hasText(token)) {
            //不需要token的路由可以直接放行
            filterChain.doFilter(request, response);
            return;
        }

        Result<AccessTokenUser> result = null;
        try {
            result = oAuth2Api.getUser(token);
        } catch (FeignException.Unauthorized e) {
            result = ResultFactory.of(DefaultResultCode.FEIGN_REMOTE_CALL_FAILED.getCode(), e.getMessage());
        }
        if (!result.isSuccess()) {
            response.getWriter().write(JSONObject.toJSONString(result));
            return;
        }

        final AccessTokenUser tokenUser = result.getData();
        final String username = tokenUser.getUsername();
        final OauthUserDTO user = customTokenService.convertUser(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user, null, tokenUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
