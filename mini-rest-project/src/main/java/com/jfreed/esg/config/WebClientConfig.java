package com.jfreed.esg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig
{

    @Value("${api.scheme}")
    private String apiScheme;

    @Value("${server.address}")
    private String address;

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
