package com.martin.aleksandrov.EVChargingStationAPI.api;

import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationDto;
import com.martin.aleksandrov.EVChargingStationAPI.models.entities.ChargingStationEntity;
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
    public List<ChargingStationDto> getAll(){
        return this.service.getAllStations();
    }

    @PostMapping("/create")
    public void newStation(@RequestBody ChargingStationDto chargingStationDto) throws BadRequestException {
        this.service.createNewChargingStation(chargingStationDto);
    }
}
