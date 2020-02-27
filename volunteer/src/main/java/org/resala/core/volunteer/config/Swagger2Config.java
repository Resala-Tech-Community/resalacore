package org.resala.core.volunteer.config;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;



@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
