package com.martin.aleksandrov.EVChargingStationAPI.services.impl;

import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationDto;
import com.martin.aleksandrov.EVChargingStationAPI.models.entities.ChargingStationEntity;
import com.martin.aleksandrov.EVChargingStationAPI.repositories.ChargingStationRepository;
import com.martin.aleksandrov.EVChargingStationAPI.services.ChargingStationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<ChargingStationDto> getAllStations() {

        return this.chargingStationRepository.findAll()
                .stream()
                .map(station -> this.modelMapper.map(station, ChargingStationDto.class))
                .toList();
    }

    @Override
    public ChargingStationDto getStationById(String uniqueId) {
        Optional<ChargingStationEntity> entityByUniqueId = this.chargingStationRepository.findChargingStationEntityByUniqueId(uniqueId);

        if (entityByUniqueId.isPresent()) {
            return this.modelMapper.map(entityByUniqueId, ChargingStationDto.class);
        }
        return null;
    }

    @Override
    public ChargingStationDto getStationByZipcode(String zipcode) {
        Optional<ChargingStationEntity> entityByZipcode = this.chargingStationRepository.findChargingStationEntityByZipcode(zipcode);

        if (entityByZipcode.isPresent()) {
            return this.modelMapper.map(entityByZipcode, ChargingStationDto.class);
        }

        return null;
    }

    @Override
    public ChargingStationDto getStationByGeolocation(double lat, double lon) {
        return null;
    }

}
