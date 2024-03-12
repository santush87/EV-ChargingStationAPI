package com.martin.aleksandrov.EVChargingStationAPI.repositories;

import com.martin.aleksandrov.EVChargingStationAPI.models.entities.ChargingStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStationEntity, String> {
}
