package com.martin.aleksandrov.EVChargingStationAPI.services.impl;

import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationDto;
import com.martin.aleksandrov.EVChargingStationAPI.repositories.ChargingStationRepository;
import com.martin.aleksandrov.EVChargingStationAPI.services.ChargingStationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChargingStationServiceImpl implements ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean createNewChargingStation(ChargingStationDto newChargingStation) {
        return false;
    }

    @Override
    public List<ChargingStationDto> getAllStatitons() {

        List<ChargingStationDto> all = this.chargingStationRepository.findAll()
                .stream()
                .map(station -> this.modelMapper.map(station, ChargingStationDto.class))
                .toList();

        return all;
    }

    @Override
    public ChargingStationDto getStation(String uniqueId) {
        return null;
    }
}
