package com.technologyos.role.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

   @Value("${swagger.server.url}")
   private String host;

   @Value("${environment.mode}")
   private String environment;

   @Bean
   public OpenAPI openApiDoc() {
      return new OpenAPI()
         .addServersItem(new Server().description(environment).url(host))
         .components(
            new Components().addSecuritySchemes("bearer-jwt",
               new SecurityScheme()
                  .type(SecurityScheme.Type.HTTP)
                  .scheme("bearer").bearerFormat("JWT")
                  .in(SecurityScheme.In.HEADER).name("Authorization")))
         .info(new Info()
            .title("Role Service API")
            .description("API documentation of role service project")
            .version("1.0"))
         .externalDocs(new ExternalDocumentation()
            .description("Powered by technologyos")
            .url("https://github.com/ArmandoSJ"));
   }
}
