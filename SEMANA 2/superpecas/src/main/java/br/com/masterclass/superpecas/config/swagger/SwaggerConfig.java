package br.com.masterclass.superpecas.config.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("superpecas_api")
                        .version("0.0.1")
                        .description("Api para controle de carros e peças.")
                        .termsOfService("https://github.com/Fabio-Portfolio-TI/SuperPecas")
                        .contact(new Contact()
                                .name("Suporte via email")
                                .email("fabiobiotec17@gmail.com"))
                        .license(new License()
                                .name("APACHE 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                        )
                ).components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                ).security(Collections.singletonList(new SecurityRequirement().addList("bearerAuth")));
    }
}
