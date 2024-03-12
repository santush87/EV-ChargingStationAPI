package com.martin.aleksandrov.EVChargingStationAPI.models.dtos;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargingStationDto {

    @NotNull
    private String uniqueId;

    @NotNull
    private Double geoCoordinatesLat;

    @NotNull
    private Double geoCoordinatesLon;

    @NotNull
    private String zipcode;
}
