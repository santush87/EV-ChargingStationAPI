package com.martin.aleksandrov.EVChargingStationAPI.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper mapper = new ModelMapper();

        return mapper;
    }
}
