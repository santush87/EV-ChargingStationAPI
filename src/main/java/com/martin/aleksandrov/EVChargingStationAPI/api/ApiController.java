package com.martin.aleksandrov.EVChargingStationAPI.api;

import com.martin.aleksandrov.EVChargingStationAPI.exceptions.ChargingStationNotFoundException;
import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationDto;
import com.martin.aleksandrov.EVChargingStationAPI.services.ChargingStationService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final ChargingStationService service;
    private final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/all")
    public List<ChargingStationDto> getAll() {
        return this.service.getAllStations();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void newStation(@RequestBody ChargingStationDto chargingStationDto) throws BadRequestException {
        this.service.createNewChargingStation(chargingStationDto);
    }

    @GetMapping("/near-stations")
    public List<ChargingStationDto> getNearStations(@RequestParam Double lat,
                                                    @RequestParam Double lon,
                                                    @RequestParam int distanceInMeters) {

        return this.service.getNearStationsByGeolocationAndDistance(lat, lon, distanceInMeters);
    }

    @GetMapping("/charging-station/{uniqueId}")
    public ChargingStationDto getStationByUniqueId(@PathVariable String uniqueId) {
        return this.service.getStationById(uniqueId);
    }

    @GetMapping("/charging-station")
    public ChargingStationDto getStationByZipcode(@RequestParam String zipcode) {
        return this.service.getStationByZipcode(zipcode);
    }

    @DeleteMapping("/charging-station/delete")
    public void deleteStation(@RequestParam String uniqueId) {
        try {
            this.service.deleteChargingStation(uniqueId);
        } catch (ChargingStationNotFoundException exc) {
            logger.info(exc.getMessage(), exc);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
}