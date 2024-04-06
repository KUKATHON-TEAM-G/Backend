package com.kukathon.teamg.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Swagger API",
        version = "1.0",
        description = "Documentation Swagger API v1.0",
        license = @License(name = "Apache 2.0", url = "http://springdoc.org"),
        contact = @Contact(name = "Swagger Support", email = "packdev937@gmail.com")
    ),
    servers = {
        @Server(url = "http://localhost:8080/", description = "Localhost Server"),
        @Server(url = "https://packdev937.site/", description = "HTTPS Server")
    }
)
@SecuritySchemes({
    @SecurityScheme(
        name = "Bearer Token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "JWT Bearer Token.."
    )
})
@Configuration
public class SwaggerConfig {

}