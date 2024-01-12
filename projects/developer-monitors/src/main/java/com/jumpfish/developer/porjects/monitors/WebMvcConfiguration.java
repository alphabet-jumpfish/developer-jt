package com.jumpfish.developer.porjects.monitors;

import com.jumpfish.developer.porjects.monitors.access.AccessUserMethodArgumentResolver;
import com.jumpfish.developer.porjects.monitors.layerpage.PageLayerMethodArgumentResolver;
import com.jumpfish.developer.porjects.monitors.layertime.TimeLayerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 解决 mvc request 请求链路 自定义 resolvers
 * [方法级别]
 */
@Configuration
public class WebMvcConfiguration {

    @Bean
    public WebMvcConfigurer httpConfiguration(
            List<HandlerMethodArgumentResolver> handlerMethodArgumentResolvers
    ) {

        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
            }
            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
                List<HandlerMethodArgumentResolver> filters = handlerMethodArgumentResolvers.stream().filter(handler_resolvers -> {
                    if (handler_resolvers instanceof AccessUserMethodArgumentResolver) {
                        return true;
                    }
                    if (handler_resolvers instanceof PageLayerMethodArgumentResolver) {
                        return true;
                    }
                    if (handler_resolvers instanceof TimeLayerMethodArgumentResolver) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
                resolvers.addAll(filters);
            }
        };
        return webMvcConfigurer;
    }

}
