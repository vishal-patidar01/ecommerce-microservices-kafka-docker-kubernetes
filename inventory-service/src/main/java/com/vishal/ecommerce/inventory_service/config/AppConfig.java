package com.vishal.ecommerce.inventory_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return  new ModelMapper();
    }

    @Bean
    public RestClient restClient() {
        return RestClient.builder().build();
    }
}
