package com.keretaapi.transaction_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced // Anotasi ini PENTING! Agar WebClient bisa menggunakan nama service dari Eureka
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}