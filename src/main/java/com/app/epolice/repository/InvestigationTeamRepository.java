package com.app.epolice.repository;

import com.app.epolice.model.entity.policestation.InvestigationTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Investigation team repository.
 */
@Repository
public interface InvestigationTeamRepository extends JpaRepository<InvestigationTeam, Long> {
    /**
     * Find all by active list.
     *
     * @param active the active
     * @return the list
     */
    List<InvestigationTeam> findAllByActive(boolean active);
}
