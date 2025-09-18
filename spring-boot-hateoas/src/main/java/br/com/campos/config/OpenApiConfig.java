package br.com.campos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API RESTFULL JAVA SPRING BOOT")
                        .version("V1")
                        .description("Rest API Spring Boot Java com kubernets and docker")
                        .termsOfService("https://github.com/mateusribeirocampos")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/mateusribeirocampos"))
                );
    }

}
