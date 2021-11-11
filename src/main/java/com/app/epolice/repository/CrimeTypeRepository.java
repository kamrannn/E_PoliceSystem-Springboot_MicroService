package com.app.epolice.repository;

import com.app.epolice.model.entity.crime.CrimeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * The interface Crime type repository.
 */
@Repository
public interface CrimeTypeRepository extends JpaRepository<CrimeType,Long> {
    /**
     * Find all by active list.
     *
     * @param active the active
     * @return the list
     */
    List<CrimeType> findAllByActive(boolean active);

    /**
     * Find all by active true order by created date desc list.
     *
     * @return the list
     */
    List<CrimeType> findAllByActiveTrueOrderByCreatedDateDesc();

    /**
     * Find all crime types by date list.
     *
     * @param date the date
     * @return the list
     */
    @Query(value = "SELECT * FROM t_crime_type where created_date like CONCAT(:date,'%')", nativeQuery = true)
    List<CrimeType> findAllCrimeTypesByDate(Date date);
}
