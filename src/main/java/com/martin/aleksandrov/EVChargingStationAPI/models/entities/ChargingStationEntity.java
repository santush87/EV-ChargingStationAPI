package com.martin.aleksandrov.EVChargingStationAPI.models.entities;

import jakarta.persistence.*;
import lombok.Getter;

import org.hibernate.annotations.GenericGenerator;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

@Entity
@Table(name = "charging_station")
@Getter
public class ChargingStationEntity {

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(unique = true)
    private String uniqueId;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String zipcode;

    @Column(nullable = false)
    private Point point;

    public ChargingStationEntity setId(String id) {
        this.id = id;
        return this;
    }

    public ChargingStationEntity setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public ChargingStationEntity setLatitude(Double latitude) {
        this.latitude = latitude;
        recalculate();
        return this;
    }

    public ChargingStationEntity setLongitude(Double longitude) {
        this.longitude = longitude;
        recalculate();
        return this;
    }

    public ChargingStationEntity setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public ChargingStationEntity setPoint(Point point) {
        this.point = point;
        this.latitude = point.getY();
        this.longitude = point.getX();
        return this;
    }

    // create the recalculate method to update gemPoint accoring to current latitude and longitude values
    private void recalculate() {
        if (latitude != null && longitude != null) {
            GeometryFactory geomFactory = new GeometryFactory(new PrecisionModel(), 4326);
            this.point = geomFactory.createPoint(new Coordinate(longitude, latitude));
        }
    }
}
