package com.app.epolice.repository;

import com.app.epolice.model.entity.crime.CrimeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrimeTypeRepository extends JpaRepository<CrimeType,Long> {
    List<CrimeType> findAllByActive(boolean active);
}
