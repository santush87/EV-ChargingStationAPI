package com.martin.aleksandrov.EVChargingStationAPI.config;

import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationDto;
import com.martin.aleksandrov.EVChargingStationAPI.models.entities.ChargingStationEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper mapper = new ModelMapper();

//        mapper.addMappings(new PropertyMap<ChargingStationDto, ChargingStationEntity>() {
//            @Override
//            protected void configure() {
//                skip(destination.setPoint());
//            }
//        });
        return mapper;
    }
}
