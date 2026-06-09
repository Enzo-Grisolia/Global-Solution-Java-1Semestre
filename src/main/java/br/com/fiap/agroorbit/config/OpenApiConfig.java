package br.com.fiap.agroorbit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI agroOrbitOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AgroOrbit Core API")
                        .description("API REST de monitoramento agrícola por satélite. "
                                + "Gerencia produtores, propriedades, leituras de satélite "
                                + "e alertas climáticos. FIAP - Global Solution 2026.1.")
                        .version("2.0.0")
                        .contact(new Contact()
                                .name("Equipe AgroOrbit Core - 3SIR")));
    }
}
