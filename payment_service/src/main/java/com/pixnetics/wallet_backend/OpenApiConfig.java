package com.pixnetics.wallet_backend;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI walletOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Offline Wallet Platform API")
                        .description("REST API documentation for the Wallet Platform")
                        .version("1.0.0")
                )
                .tags(List.of(
                        new Tag()
                                .name("Platform Management")
                                .description("Platform administration and merchant registration"),

                        new Tag()
                                .name("Merchant Management")
                                .description("Merchant management and terminal registration"),

                        new Tag()
                                .name("Client Management")
                                .description("Client registration and management"),

                        new Tag()
                                .name("Wallet Management")
                                .description("Wallet creation and balance management"),

                        new Tag()
                                .name("Terminal Operations")
                                .description("Terminal authentication and operations"),

                        new Tag()
                                .name("Payment Management")
                                .description("Payment processing and transaction management")
                ));
    }
}
