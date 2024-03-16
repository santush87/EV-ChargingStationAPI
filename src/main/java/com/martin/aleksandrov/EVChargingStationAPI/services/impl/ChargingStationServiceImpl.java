package com.martin.aleksandrov.EVChargingStationAPI.services.impl;

import com.martin.aleksandrov.EVChargingStationAPI.exceptions.ChargingStationNotFoundException;
import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationCreateDto;
import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationDto;
import com.martin.aleksandrov.EVChargingStationAPI.models.entities.ChargingStationEntity;
import com.martin.aleksandrov.EVChargingStationAPI.repositories.ChargingStationRepository;
import com.martin.aleksandrov.EVChargingStationAPI.services.ChargingStationService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChargingStationServiceImpl implements ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;
    private final ModelMapper modelMapper;

    @Override
    public ChargingStationDto createNewChargingStation(ChargingStationCreateDto newChargingStation) throws BadRequestException {
        Optional<ChargingStationEntity> optionalEntity =
                this.chargingStationRepository
                        .findChargingStationEntityByUniqueId(newChargingStation.getUniqueId());

        if (optionalEntity.isPresent()) {
            throw new BadRequestException("Duplicate identifier");
        }

        ChargingStationEntity chargingStation =
                this.modelMapper.map(newChargingStation, ChargingStationEntity.class);

        ChargingStationEntity savedEntity = this.chargingStationRepository.save(chargingStation);

        System.out.println(savedEntity.getPoint());
        ChargingStationDto stationDto = this.modelMapper.map(savedEntity, ChargingStationDto.class);
        System.out.println("-----------");
        System.out.println(stationDto.toString());
        return stationDto;
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
        Optional<ChargingStationEntity> entityByUniqueId =
                this.chargingStationRepository.findChargingStationEntityByUniqueId(uniqueId);

        if (entityByUniqueId.isPresent()) {
            return this.modelMapper.map(entityByUniqueId, ChargingStationDto.class);
        }
        return null;
    }

    @Override
    public ChargingStationDto getStationByZipcode(String zipcode) {
        Optional<ChargingStationEntity> entityByZipcode =
                this.chargingStationRepository.findChargingStationEntityByZipcode(zipcode);

        if (entityByZipcode.isPresent()) {
            return this.modelMapper.map(entityByZipcode, ChargingStationDto.class);
        }

        return null;
    }

    @Override
    public List<ChargingStationDto> getNearStationsByGeolocationAndDistance(double lat, double lon, int distanceInMeters) {
        return this.chargingStationRepository.findGeolocation(lat, lon, distanceInMeters).stream()
                .map(location -> this.modelMapper.map(location, ChargingStationDto.class)).toList();
    }


    @Override
    @Transactional
    public void deleteChargingStation(String uniqueId) {
        Optional<ChargingStationEntity> byUniqueId = this.chargingStationRepository.findChargingStationEntityByUniqueId(uniqueId);
        if (byUniqueId.isPresent()) {

           this.chargingStationRepository.deleteByUniqueId(uniqueId);
        } else {

            throw new ChargingStationNotFoundException("Missing charging station with such id");
        }

    }
}
