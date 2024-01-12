package com.jumpfish.developer.porjects.monitors.access;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jumpfish.developer.porjects.monitors.access.request.SecurityContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
public class AccessUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (!methodParameter.hasParameterAnnotation(AccessUser.class)) {
            return false;
        }
        Class clazz = methodParameter.getParameterType();
        return CurrentUser.class.isAssignableFrom(clazz);
    }

    @Nullable
    @Override
    public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, @Nullable WebDataBinderFactory webDataBinderFactory) {
        String accessToken = SecurityContextUtils.getCurrentUserToken();
        String currentUsername = SecurityContextUtils.getCurrentUsername();
        String tenantId = SecurityContextUtils.getCurrentTenantId();
        String userId = SecurityContextUtils.getCurrentUserId();

        log.info("用户实体解析器:accessToken:{}:currentUsername:{}:tenantId:{}:userId:{}", accessToken, currentUsername, tenantId, userId);
        return new CurrentUser().
                setAccessToken(accessToken).
                setUserName(currentUsername).
                setTenantId(StringUtils.isNotBlank(tenantId) ? Long.valueOf(tenantId) : null).
                setUserId(StringUtils.isNotBlank(userId) ? Long.valueOf(userId) : null);
    }
}
