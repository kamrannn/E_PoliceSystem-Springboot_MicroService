package com.app.epolice.service;

import com.app.epolice.repository.PoliceStationRepository;
import org.springframework.stereotype.Service;

@Service
public class PoliceStationService {

    PoliceStationRepository policeStationRepository;

    public PoliceStationService(PoliceStationRepository policeStationRepository) {
        this.policeStationRepository = policeStationRepository;
    }
}
