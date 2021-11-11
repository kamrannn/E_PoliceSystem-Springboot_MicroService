package com.app.epolice.repository;

import com.app.epolice.model.entity.policestation.InvestigationTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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

    /**
     * Find all by active true order by created date desc list.
     *
     * @return the list
     */
    List<InvestigationTeam> findAllByActiveTrueOrderByCreatedDateDesc();

    /**
     * Find all investigation teams by date list.
     *
     * @param date the date
     * @return the list
     */
    @Query(value = "SELECT * FROM t_investigation_team where created_date like CONCAT(:date,'%')", nativeQuery = true)
    List<InvestigationTeam> findAllInvestigationTeamsByDate(Date date);
}
