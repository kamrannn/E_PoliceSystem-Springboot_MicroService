package com.app.epolice.repository;

import com.app.epolice.model.entity.policestation.InvestigationTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestigationTeamRepository extends JpaRepository<InvestigationTeam, Long> {
    List<InvestigationTeam> findAllByActive(boolean active);
}
