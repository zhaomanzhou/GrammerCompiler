package com.idofast.admin.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaomanzhou
 */

@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2Configuration {
    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("token").description("用户凭证")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false)
                .defaultValue("eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE2NDQxMTkyNDcsImlhdCI6MTYxMjU4MzI0N30.ChCUn8zBVsm6ktrp33g73hxpf7__g7DXPOr0PJsIta8")
                .build();
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.idofast.admin.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)  ;
    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("IDoFast前后端交互api")
                .description("IDoFast后端api列表")
                .termsOfServiceUrl("http://localhost:8001/")
                .version("1.0")
                .build();
    }
}
