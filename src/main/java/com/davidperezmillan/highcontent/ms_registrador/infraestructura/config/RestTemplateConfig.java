// src/main/java/com/davidperezmillan/highcontent/ms_registrador/config/RestTemplateConfig.java
package com.davidperezmillan.highcontent.ms_registrador.infraestructura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}