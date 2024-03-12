package com.martin.aleksandrov.EVChargingStationAPI.repositories;

import com.martin.aleksandrov.EVChargingStationAPI.models.entities.ChargingStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStationEntity, String> {

    Optional<ChargingStationEntity> findChargingStationEntityByUniqueId(String uniqueId);

    Optional<ChargingStationEntity> findChargingStationEntityByZipcode(String zipcode);
    @Query(value = "SELECT * FROM charging_station WHERE st_distance_sphere(point, ST_SRID( Point(:lon, :lat), 4326)) <= :distance LIMIT 100", nativeQuery = true)
    List<ChargingStationEntity> findGeolocation(double lat, double lon, int distance);

    void deleteByUniqueId(String uniqueId);
}
