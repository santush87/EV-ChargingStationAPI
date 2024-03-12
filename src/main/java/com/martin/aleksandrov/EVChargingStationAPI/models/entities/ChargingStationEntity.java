package com.martin.aleksandrov.EVChargingStationAPI.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "charging_station")
@Getter
@Setter
public class ChargingStationEntity {

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(unique = true)
    private String uniqueId;

    @Column(nullable = false)
    private Double geoCoordinatesLat;

    @Column(nullable = false)
    private Double geoCoordinatesLon;

    @Column(nullable = false)
    private String zipcode;
}
