package de.lyth.vehicleapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    public static final Contact DEFAULT_CONTACT = new Contact(
            "Daniel Ramke",
            "https://github.com/danielramke/",
            "daniel.ramke@outlook.com"
    );

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Vehicle API",
            "Spring Boot Vehicle API",
            "0.0.1",
            null,
            DEFAULT_CONTACT,
            "Copyright © 2012 - 2020, Udacity Inc.",
            "https://github.com/danielramke/Web-Services-And-Apis/blob/main/LICENSE.txt",
            new ArrayList<>()
    );

}
