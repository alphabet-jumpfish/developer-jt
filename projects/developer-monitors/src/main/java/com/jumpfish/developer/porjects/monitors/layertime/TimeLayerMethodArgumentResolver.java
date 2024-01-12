package com.jumpfish.developer.porjects.monitors.layertime;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jumpfish.developer.porjects.monitors.access.request.SecurityContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Date;

@Slf4j
@Component
public class TimeLayerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (!methodParameter.hasParameterAnnotation(TimeLayerAnnotation.class)) {
            return false;
        }
        Class clazz = methodParameter.getParameterType();
        return TimeLayer.class.isAssignableFrom(clazz);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String requestTime = SecurityContextUtils.getRequestTime();
        String requestAdjustTime = SecurityContextUtils.getRequestAdjustTime();
        Long rt = StringUtils.isNotBlank(requestTime) ? Long.valueOf(requestTime) : null;
        Integer rat = StringUtils.isNotBlank(requestAdjustTime) ? Long.valueOf(requestAdjustTime).intValue() : null;
        if (rat != null && rt != null) {
            Date current_time = new Date();
            Date c_max_rt = DateUtil.offsetMillisecond(current_time, rat);
            Date c_min_rt = DateUtil.offsetMillisecond(current_time, -rat);
            log.info("时间解析器:请求时间:{},误差允许时间:{}---{}", DateUtil.format(new Date(rt), "YYYY-MM-DD HH:mm:ss"), c_min_rt, c_max_rt);
            return new TimeLayer()
                    .setRequest_time(StringUtils.isNotBlank(requestTime) ? Long.valueOf(requestTime) : null);

        } else {
            return null;
        }
    }
}
