package com.jumpfish.developer.porjects.monitors.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .protocols(new HashSet(Arrays.asList("http", "https")))
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("开发联盟")
                .description("开发联盟手册")
                .licenseUrl("UNKNOW")
                .version("1.0.0" + " spring boot  Version " + SpringBootVersion.getVersion())
                .build();
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContextList = new ArrayList<>();

        List<SecurityReference> securityReferenceList = new ArrayList<>();

        securityReferenceList.add(
                new SecurityReference(SystemHeaderConstant.ACCESS_TOKEN, new AuthorizationScope[]{new AuthorizationScope("global", "accessAnything")}));
        securityReferenceList.add(
                new SecurityReference(SystemHeaderConstant.KEY_NAME_TENANT_ID, new AuthorizationScope[]{new AuthorizationScope("global", "accessAnything")})
        );
        securityReferenceList.add(
                new SecurityReference(SystemHeaderConstant.KEY_NAME_USER_ID, new AuthorizationScope[]{new AuthorizationScope("global", "accessAnything")})
        );

        securityContextList.add(SecurityContext
                .builder()
                .securityReferences(securityReferenceList)
                .forPaths(PathSelectors.any())
                .build()
        );
        return securityContextList;
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey(SystemHeaderConstant.ACCESS_TOKEN, SystemHeaderConstant.ACCESS_TOKEN, "header"));
        return apiKeyList;
    }
}
