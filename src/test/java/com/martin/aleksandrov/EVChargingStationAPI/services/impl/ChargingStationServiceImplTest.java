package com.martin.aleksandrov.EVChargingStationAPI.services.impl;

import com.martin.aleksandrov.EVChargingStationAPI.exceptions.ChargingStationNotFoundException;
import com.martin.aleksandrov.EVChargingStationAPI.models.dtos.ChargingStationDto;
import com.martin.aleksandrov.EVChargingStationAPI.repositories.ChargingStationRepository;
import com.martin.aleksandrov.EVChargingStationAPI.services.ChargingStationService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChargingStationServiceImplTest {

    @Autowired
    private ChargingStationService stationService;

    @Autowired
    private ChargingStationRepository stationRepository;

    private final ChargingStationDto stationDto = new ChargingStationDto();

    private static final String TEST_ID = "id_1";
    private static final double TEST_LATITUDE = 43.026859;
    private static final double TEST_LONGITUDE = 25.099304;
    private static final String TEST_ZIPCODE = "5400";

    @BeforeEach
    void setUp() throws BadRequestException {
        this.stationRepository.deleteAll();

        stationDto.setUniqueId(TEST_ID);
        stationDto.setLatitude(TEST_LATITUDE);
        stationDto.setLongitude(TEST_LONGITUDE);
        stationDto.setZipcode(TEST_ZIPCODE);

        this.stationService.createNewChargingStation(stationDto);
    }

    @AfterEach
    void tearDown() {
        this.stationRepository.deleteAll();
    }

    @Test
    void testCreateChargingStationSuccessful() throws BadRequestException {
        ChargingStationDto stationTestDto = new ChargingStationDto();
        stationTestDto.setUniqueId("id_2");
        stationTestDto.setLatitude(64.246);
        stationTestDto.setLongitude(62.157);
        stationTestDto.setZipcode("5461");

//        The repository has 1 charging station at start
        assertEquals(1, this.stationRepository.count(),
                "Test before adding the new charging station");

//        Creating a new station and now the repository count must be 1
        this.stationService.createNewChargingStation(stationTestDto);
        assertEquals(2, this.stationRepository.count(),
                "Test after adding the new charging station");
    }

    @Test
    void testCreateChargingStationThrows() {
        assertThrows(BadRequestException.class,
                () -> this.stationService.createNewChargingStation(stationDto),
                "Creating a charging station that already exists.");
    }

    @Test
    void testGetAllChargingStations() throws BadRequestException {
        // There is 1 charging station from @BeforeEach
        assertEquals(1, this.stationService.getAllStations().size(),
                "Test before adding the new charging station");

        ChargingStationDto stationTestDto = new ChargingStationDto();
        stationTestDto.setUniqueId("id_2");
        stationTestDto.setLatitude(64.246);
        stationTestDto.setLongitude(62.157);
        stationTestDto.setZipcode("5461");

        this.stationService.createNewChargingStation(stationTestDto);

//        After adding one more charging station, the size must be 2
        assertEquals(2, this.stationService.getAllStations().size(),
                "Test after adding the new charging station");
    }

    @Test
    void testGetStationById() {
        ChargingStationDto stationById = this.stationService.getStationById(TEST_ID);

        assertEquals(stationDto.getUniqueId(), stationById.getUniqueId(),
                "Test shows if unique_ids between stationDto and the stationById are same!");
        assertEquals(stationDto.getZipcode(), stationById.getZipcode(),
                "Test shows if zipcodes between stationDto and the stationById are same!");
        assertEquals(stationDto.getLatitude(), stationById.getLatitude(),
                "Test shows if latitudes between stationDto and the stationById are same!");
        assertEquals(stationDto.getLongitude(), stationById.getLongitude(),
                "Test shows if longitudes between stationDto and the stationById are same!");
    }

    @Test
    void testGetStationByIdReturnNull() {
        ChargingStationDto stationById = this.stationService.getStationById("id_1486");
        assertNull(stationById, "Testing with false id");
    }

    @Test
    void testGetStationByZipcode() {
        ChargingStationDto stationByZipcode = this.stationService.getStationByZipcode(TEST_ZIPCODE);
        assertEquals(stationDto.getUniqueId(), stationByZipcode.getUniqueId(),
                "Test shows if unique_ids between stationDto and the stationById are same!");
        assertEquals(stationDto.getZipcode(), stationByZipcode.getZipcode(),
                "Test shows if zipcodes between stationDto and the stationById are same!");
        assertEquals(stationDto.getLatitude(), stationByZipcode.getLatitude(),
                "Test shows if latitudes between stationDto and the stationById are same!");
        assertEquals(stationDto.getLongitude(), stationByZipcode.getLongitude(),
                "Test shows if longitudes between stationDto and the stationById are same!");
    }

    @Test
    void testGetStationByZipcodeReturnNull() {
        ChargingStationDto stationByZipcode = this.stationService.getStationByZipcode("11111");
        assertNull(stationByZipcode, "Testing with false zipcode");
    }

    @Test
    void deleteChargingStationSuccessful() {
        assertEquals(1, this.stationRepository.count(),
                "Test before delete operation. There must have 1 charging station");

        this.stationService.deleteChargingStation(TEST_ID);
        assertEquals(0, this.stationRepository.count(),
                "Test after delete operation. There must have 0 charging station");
    }

    @Test
    void testDeleteChargingStationThrows() {
        assertThrows(ChargingStationNotFoundException.class,
                () -> this.stationService.deleteChargingStation("aaaaaa"),
                "Test with not existing uniqueId");
    }

    @Test
    void testGetNearStationsByGeolocationAndDistance() throws BadRequestException {
        ChargingStationDto stationTestDtoSec = new ChargingStationDto();
        stationTestDtoSec.setUniqueId("id_Gabrovo");
        stationTestDtoSec.setLatitude(42.873499);
        stationTestDtoSec.setLongitude(25.318237);
        stationTestDtoSec.setZipcode("5300");

        this.stationService.createNewChargingStation(stationTestDtoSec);

//        Testing parameters for searching charging stations near
        double latitude = 42.975938;
        double longitude = 25.126348;
        int twoKm = 2000;
        int tenKm = 10000;
        int twentyKm = 20000;

//        There is no charging station in range of 2000m (2km)
        assertEquals(0, this.stationService.getNearStationsByGeolocationAndDistance(latitude, longitude, twoKm).size(),
                "There is no charging station in range of 2km");

//        There is 1 charging station in range of 10000m (10km)
        assertEquals(1, this.stationService.getNearStationsByGeolocationAndDistance(latitude, longitude, tenKm).size(),
                "There must be 1 charging station in range of 10km");

//        There is 2 charging station in range of 2000m (20km)
        assertEquals(2, this.stationService.getNearStationsByGeolocationAndDistance(latitude, longitude, twentyKm).size(),
                "There must be 2 charging station in range of 20km");
    }
}