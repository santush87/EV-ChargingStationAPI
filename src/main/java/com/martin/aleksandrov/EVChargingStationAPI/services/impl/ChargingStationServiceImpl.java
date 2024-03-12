package com.martin.aleksandrov.EVChargingStationAPI.services.impl;

import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationDto;
import com.martin.aleksandrov.EVChargingStationAPI.models.entities.ChargingStationEntity;
import com.martin.aleksandrov.EVChargingStationAPI.repositories.ChargingStationRepository;
import com.martin.aleksandrov.EVChargingStationAPI.services.ChargingStationService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
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
    public void createNewChargingStation(ChargingStationDto newChargingStation) throws BadRequestException {
        Optional<ChargingStationEntity> optionalEntity =
                this.chargingStationRepository
                        .findChargingStationEntityByUniqueId(newChargingStation.getUniqueId());

        if (optionalEntity.isPresent()) {
            throw new BadRequestException("Dublicate identifier");
        }

        ChargingStationEntity chargingStation =
                this.modelMapper.map(newChargingStation, ChargingStationEntity.class);

        this.chargingStationRepository.save(chargingStation);

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

//    @Override
//    public void getStationByGeolocation(double lat, double lon, int distance) {
//        Optional<ChargingStationEntity> geoCoordinates = this.chargingStationRepository.findChargingStationEntityByGeoCoordinatesLatAndGeoCoordinatesLon(lat, lon, distance);
//
////        return null;
//    }

    @Override
    public void deleteChargingStation(String uniqueId) {
        this.chargingStationRepository.deleteByUniqueId(uniqueId);
    }

}
