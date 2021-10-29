package com.app.epolice.repository;

import com.app.epolice.model.entity.CrimeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrimeReportRepository extends JpaRepository<CrimeReport, Long> {
}
