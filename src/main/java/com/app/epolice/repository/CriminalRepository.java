package com.app.epolice.repository;

import com.app.epolice.model.entity.crime.Criminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface CriminalRepository extends JpaRepository<Criminal, Long> {
    List<Criminal> findAllByActive(boolean active);
    Optional<Criminal> findByCnic(String cnic);
}
