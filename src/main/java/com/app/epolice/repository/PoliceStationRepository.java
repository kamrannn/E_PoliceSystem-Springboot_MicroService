package com.app.epolice.repository;

import com.app.epolice.model.entity.policestation.PoliceStation;
import com.app.epolice.model.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * The interface Police station repository.
 */
@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation,Long> {
    /**
     * Find police stations by active list.
     *
     * @param active the active
     * @return the list
     */
    List<PoliceStation> findPoliceStationsByActive(boolean active);

    /**
     * Find all by active true order by created date desc list.
     *
     * @return the list
     */
    List<PoliceStation> findAllByActiveTrueOrderByCreatedDateDesc();

    /**
     * Find all police stations by date list.
     *
     * @param date the date
     * @return the list
     */
    @Query(value = "SELECT * FROM t_police_station where created_date like CONCAT(:date,'%')", nativeQuery = true)
    List<PoliceStation> findAllPoliceStationsByDate(Date date);
}
