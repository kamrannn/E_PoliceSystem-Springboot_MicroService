package com.app.epolice.repository;

import com.app.epolice.model.entity.crime.CrimeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Crime report repository.
 */
@Repository
public interface CrimeReportRepository extends JpaRepository<CrimeReport, Long> {
    /**
     * Find all by active list.
     *
     * @param active the active
     * @return the list
     */
    List<CrimeReport> findAllByActive(boolean active);

    /**
     * Find all by id list.
     *
     * @param id the id
     * @return the list
     */
    List<CrimeReport> findAllById(long id);

    /**
     * Find all by active true order by created date desc list.
     *
     * @return the list
     */
    List<CrimeReport> findAllByActiveTrueOrderByCreatedDateDesc();
}
