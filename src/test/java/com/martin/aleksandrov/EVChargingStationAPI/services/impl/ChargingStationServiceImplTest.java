package com.martin.aleksandrov.EVChargingStationAPI.services.impl;

import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationCreateDto;
import com.martin.aleksandrov.EVChargingStationAPI.repositories.ChargingStationRepository;
import com.martin.aleksandrov.EVChargingStationAPI.services.ChargingStationService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ChargingStationServiceImplTest {

    @Autowired
    private ChargingStationService stationService;

    @Autowired
    private ChargingStationRepository stationRepository;

    @BeforeEach
    void setUp() {
        this.stationRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        this.stationRepository.deleteAll();
    }

    @Test
    void testCreateChargingStationSuccessful() throws BadRequestException {
        ChargingStationCreateDto stationTestDto = new ChargingStationCreateDto();
        stationTestDto.setUniqueId("id_1");
        stationTestDto.setLatitude(64.246);
        stationTestDto.setLongitude(62.157);
        stationTestDto.setZipcode("5461");

//        The repository is empty and must be = 0
        assertEquals(0, this.stationRepository.count());


//        Creating a new station and now the repository count must be 1
        this.stationService.createNewChargingStation(stationTestDto);
        assertEquals(1, this.stationRepository.count());
    }
}