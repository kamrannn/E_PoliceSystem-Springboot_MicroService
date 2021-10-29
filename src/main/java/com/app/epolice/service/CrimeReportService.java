package com.app.epolice.service;

import com.app.epolice.repository.CrimeReportRepository;
import org.springframework.stereotype.Service;

@Service
public class CrimeReportService {
    CrimeReportRepository crimeReportRepository;

    public CrimeReportService(CrimeReportRepository crimeReportRepository) {
        this.crimeReportRepository = crimeReportRepository;
    }
}
