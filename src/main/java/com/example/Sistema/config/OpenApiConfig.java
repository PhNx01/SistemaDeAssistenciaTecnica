package com.example.Sistema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Assistência Técnica API")
                        .version("0.0.1")
                        .description("API REST para gerenciamento de clientes, aparelhos, serviços e ordens de serviço.")
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento")
                                .email("dev@example.com")
                                .url("https://example.com"))
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"))
                );
    }
}

