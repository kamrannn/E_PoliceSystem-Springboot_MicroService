package com.app.epolice.repository;

import com.app.epolice.model.entity.policestation.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
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
}
