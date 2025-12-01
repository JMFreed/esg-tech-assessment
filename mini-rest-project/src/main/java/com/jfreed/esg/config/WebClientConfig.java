package com.jfreed.esg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig
{

    // Would want to implement TLS for production to use certificates
    @Value("${api.scheme}")
    private String apiScheme;

    // Would probably be something like https://esg-customer-service on K8S cluster
    @Value("${server.address}")
    private String address;

    // If service running on Kubernetes cluster, probably wouldn't need this
    @Value("${server.port}")
    private int port;

    @Bean
    public WebClient webClient()
    {
        String baseUrl = String.format("%s://%s:%s", apiScheme, address, port);
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
