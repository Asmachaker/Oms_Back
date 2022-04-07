package com.demo.oms.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class SwaggerConfig extends WebMvcConfigurerAdapter {

  /*  @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("tn.hitsolutions.logisticsolution.backOfficeMarahbe.controller"))
                //.paths(regex("/api/ReseauxAPI.*"))
                .build();
    }*/

    @Bean
    public Docket APIs() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metadata())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demo.oms.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("OMS REST API'S")
                .description("Contact : Chaker Asma")
                .version("1.0.0")
                .build();
    }

    /*private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo("Logistic Server REST API", "Delivery Center REST API", "3.0",
                "Terms of service",new Contact("a","a","a"),
                "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }*/
}