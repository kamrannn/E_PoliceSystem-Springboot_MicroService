package com.app.epolice.repository;

import com.app.epolice.model.entity.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliceStationRepository extends JpaRepository<PoliceStation,Long> {
}
