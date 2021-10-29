package com.app.epolice.repository;

import com.app.epolice.model.entity.policestation.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation,Long> {
    List<PoliceStation> findPoliceStationsByActive(boolean active);
}
