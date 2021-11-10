package com.app.epolice.repository;

import com.app.epolice.model.entity.crime.Criminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * The interface Criminal repository.
 */
@Repository
public interface CriminalRepository extends JpaRepository<Criminal, Long> {
    /**
     * Find all by active list.
     *
     * @param active the active
     * @return the list
     */
    List<Criminal> findAllByActive(boolean active);

    /**
     * Find by cnic optional.
     *
     * @param cnic the cnic
     * @return the optional
     */
    Optional<Criminal> findByCnic(String cnic);
}
