package com.app.epolice.repository;

import com.app.epolice.model.entity.crime.CrimeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
