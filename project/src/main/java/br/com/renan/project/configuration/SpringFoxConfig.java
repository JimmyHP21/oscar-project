package br.com.renan.project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    @Primary
    public Docket apiShoe() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.renan.project.application.resources"))
                .paths(PathSelectors.ant("/v1/shoe/**"))
                .build()
                .groupName("Shoe")
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        Arrays.asList(new ResponseMessageBuilder()
                                .code(500)
                                .message("Server Error")
                                .build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Projeto Oscar",
                "Projeto para avaliação Oscar",
                "1.0",
                "",
                new Contact("Renan",
                        "",
                        "renan.henriqu@hotmail.com"),
                "License of Renan Peres",
                "",
                Collections.emptyList());
    }

}
