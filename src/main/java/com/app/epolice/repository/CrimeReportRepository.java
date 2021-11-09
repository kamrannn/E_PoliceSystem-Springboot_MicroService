package com.app.epolice.repository;

import com.app.epolice.model.entity.crime.CrimeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrimeReportRepository extends JpaRepository<CrimeReport, Long> {
    List<CrimeReport> findAllByActive(boolean active);
    List<CrimeReport> findAllById(long id);
}
