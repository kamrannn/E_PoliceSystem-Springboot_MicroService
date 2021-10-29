package com.app.epolice.repository;

import com.app.epolice.model.entity.crime.CrimeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrimeTypeRepository extends JpaRepository<CrimeType,Long> {
}
