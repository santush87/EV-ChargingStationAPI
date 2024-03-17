package com.martin.aleksandrov.EVChargingStationAPI.services;

import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationDto;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ChargingStationService {

    void createNewChargingStation(ChargingStationDto newChargingStation) throws BadRequestException;
    List<ChargingStationDto> getAllStations();
    ChargingStationDto getStationById(String uniqueId);
    ChargingStationDto getStationByZipcode(String zipcode);
    List<ChargingStationDto> getNearStationsByGeolocationAndDistance(double lat, double lon, int distanceInMeters);
    void deleteChargingStation(String uniqueId);
}
