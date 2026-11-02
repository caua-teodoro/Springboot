package br.edu.dio.webapi.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        // 1. Configuração do Contato
        Contact contato = new Contact()
                .name("Suporte Técnico")
                .email("suporte@suaempresa.com")
                .url("https://suaempresa.com");

        // 2. Configuração da Licença
        License licenca = new License()
                .name("Apache 2.0")
                .url("https://apache.org");

        // 3. Configuração dos Servidores (Ambientes)
        Server localServer = new Server().url("http://localhost:8080").description("Ambiente Local");
        Server prodServer = new Server().url("https://suaempresa.com").description("Ambiente de Produção");

        return new OpenAPI()
                // Define os servidores para testar direto pelo Swagger UI
                .servers(List.of(localServer, prodServer))

                // Agrupa metadados da API (Título, Versão, Contato, etc.)
                .info(new Info()
                        .title("My App API")
                        .version("1.0")
                        .description("API documentation using Springdoc OpenAPI")
                        .termsOfService("https://suaempresa.com")
                        .contact(contato)
                        .license(licenca))

                // 4. BÔNUS: Adiciona autenticação JWT/Bearer Token global se precisar
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
                                .name("BearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}

