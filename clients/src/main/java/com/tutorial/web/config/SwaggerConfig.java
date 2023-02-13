package com.tutorial.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // private final ApplicationProperties applicationProperties;

    @Bean
    public Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tutorial.web.controller"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    // private ApiInfo apiInfo() {
    //     return new ApiInfoBuilder()
    //             .title(applicationProperties.getTitle())
    //             .description(applicationProperties.getDescription())
    //             .termsOfServiceUrl(applicationProperties.getTerms())
    //             .version(applicationProperties.getVersion())
    //             .build();
    // }
}