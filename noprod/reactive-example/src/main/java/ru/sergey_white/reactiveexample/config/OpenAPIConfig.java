package ru.sergey_white.reactiveexample.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Reactive User Management API")
                        .version("1.0")
                        .description("реактивное API для управления пользователями, построенное на Spring WebFlux.")
                        .contact(new Contact()
                                .name("Sergey White")
                                .email("sergey_white86@mail.ru")));
    }

}