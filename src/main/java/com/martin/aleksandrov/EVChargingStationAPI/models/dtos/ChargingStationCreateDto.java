package com.martin.aleksandrov.EVChargingStationAPI.models.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargingStationCreateDto {

    @NotNull
    private String uniqueId;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private String zipcode;

    @Override
    public String toString() {
        return "ChargingStationCreateDto{" +
                "uniqueId='" + uniqueId + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
