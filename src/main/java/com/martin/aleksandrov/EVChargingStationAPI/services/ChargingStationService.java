package com.martin.aleksandrov.EVChargingStationAPI.services;

import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationDto;

import java.util.List;

public interface ChargingStationService {

    boolean createNewChargingStation(ChargingStationDto newChargingStation);
    List<ChargingStationDto> getAllStatitons();
    ChargingStationDto getStation(String uniqueId);
}
