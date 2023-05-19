package com.filiperobot.aluraforumapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Forum Alura API",
        description = "Uma API Rest feita em resposta ao 4º Challenge da turma 4 do programa One \"Forum Alura\".",
        version = "2.0",
        contact = @Contact(name = "FilipeRobot", email = "filipe_lemos@live.com", url = "https://github.com/FilipeRobot"))
)
@SecurityScheme(
        name = "TokenJWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
}
