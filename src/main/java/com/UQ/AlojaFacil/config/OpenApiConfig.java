package com.UQ.AlojaFacil.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration  // Le dice a Spring que esta es una clase de configuración
@OpenAPIDefinition(// Define la información general de tu API

        info = @Info(

                title = "AlojaFacil API",  // Nombre que aparece en Swagger
                version = "1.0.0",             // Versión de tu API
                description = "API REST para la gestión pagina de alojamineto",  // Descripción
                contact = @Contact(  // Información de contacto del desarrollador
                        name = "Juan David Castillo Hernandez , Juan Diego Perez",
                        email = "alojaFacil@gmail.com"

                ),
                license = @License(  // Licencia del software
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )
        ),
        servers = {  // Define los servidores donde está disponible tu API
                @Server(
                        url = "http://localhost:8080",
                        description = "Servidor de Desarrollo"  // Este aparece en el dropdown de Swagger
                ),
        }
)
public class OpenApiConfig {

    @Bean  // Crea un objeto que Spring va a usar para configurar OpenAPI
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        // ESTO ES PARA AUTENTICACIÓN JWT (si la usas más adelante)
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)  // Tipo de autenticación HTTP
                                        .scheme("bearer")                // Esquema Bearer
                                        .bearerFormat("JWT")             // Formato del token
                                        .description("Ingresa tu token JWT")  // Descripción para el usuario
                        )
                );
    }
}
