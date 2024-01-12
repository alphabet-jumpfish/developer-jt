package com.jumpfish.developer.porjects.monitors.layerpage;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jumpfish.developer.porjects.monitors.access.request.SecurityContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
public class PageLayerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (!methodParameter.hasParameterAnnotation(PageLayerAnnotation.class)) {
            return false;
        }
        Class clazz = methodParameter.getParameterType();
        return PageLayer.class.isAssignableFrom(clazz);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String pageNo = SecurityContextUtils.getPageNo();
        String pageSize = SecurityContextUtils.getPageSize();
        log.info("分页解析器:页码:{},页大小:{}", pageNo, pageSize);
        return new PageLayer()
                .setPageNo(StringUtils.isNotBlank(pageNo) ? Long.valueOf(pageNo) : null)
                .setPageSize(StringUtils.isNotBlank(pageSize) ? Long.valueOf(pageSize) : null);
    }
}
