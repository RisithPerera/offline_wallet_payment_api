package com.pixnetics.wallet_backend;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI walletOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Offline Wallet Platform API")
                        .description("REST API documentation for the Wallet Platform")
                        .version("1.0.0")
                );
    }
}
