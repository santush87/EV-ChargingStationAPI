package com.martin.aleksandrov.EVChargingStationAPI.api;

import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationCreateDto;
import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationDto;
import com.martin.aleksandrov.EVChargingStationAPI.services.ChargingStationService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final ChargingStationService service;

    @GetMapping("/all")
    public List<ChargingStationDto> getAll() {
        return this.service.getAllStations();
    }

    @PostMapping("/create")
    public ChargingStationDto newStation(@RequestBody ChargingStationCreateDto chargingStationDto) throws BadRequestException {
        return this.service.createNewChargingStation(chargingStationDto);
    }

    @GetMapping("/near-stations")
    public List<ChargingStationDto> getNearStations(@RequestParam Double lat,
                                                    @RequestParam Double lon,
                                                    @RequestParam int distanceInMeters) {

        return this.service.getNearStationsByGeolocationAndDistance(lat, lon, distanceInMeters);
    }

    @GetMapping("/charging-station/{uniqueId}")
    public ChargingStationDto getStationByUniqueId(@PathVariable String uniqueId){
        ChargingStationDto stationById = this.service.getStationById(uniqueId);
        return stationById;
    }

    @PostMapping("/charging-station/{uniqueId}")
    public void deleteStation(@PathVariable String uniqueId){
        this.service.deleteChargingStation(uniqueId);
    }
}