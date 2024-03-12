package com.martin.aleksandrov.EVChargingStationAPI.repositories;

import com.martin.aleksandrov.EVChargingStationAPI.models.entities.ChargingStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStationEntity, String> {

    Optional<ChargingStationEntity> findChargingStationEntityByUniqueId(String uniqueId);

    Optional<ChargingStationEntity> findChargingStationEntityByZipcode(String zipcode);

    Optional<ChargingStationEntity> findChargingStationEntityByGeoCoordinatesLatAndGeoCoordinatesLon(double lat, double lon);
}
