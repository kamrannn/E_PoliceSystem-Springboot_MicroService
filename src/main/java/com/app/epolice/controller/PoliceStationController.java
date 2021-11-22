package com.app.epolice.controller;

import com.app.epolice.model.entity.policestation.PoliceStation;
import com.app.epolice.service.PoliceStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Police station controller.
 */
@EnableSwagger2
@RestController
@Validated
@RequestMapping("/police-stations")
public class PoliceStationController {
    private static final Logger LOG = LogManager.getLogger(PoliceStationController.class);

    /**
     * Initializing the objects
     */
    PoliceStationService policeStationService;

    /**
     * Instantiates a new Police station controller.
     *
     * @param policeStationService the police station service
     */
    public PoliceStationController(PoliceStationService policeStationService) {
        this.policeStationService = policeStationService;
    }

    /**
     * Showing all the police stations
     *
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfPoliceStations() {
        LOG.info("Listing all the police stations");
        return policeStationService.listAllPoliceStations();
    }

    /**
     * Adding the police stations
     *
     * @param policeStation the police station
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addPoliceStation(@Valid @RequestBody List<PoliceStation> policeStation) {
        LOG.info("Listing all the police stations");
        return policeStationService.addNewPoliceStations(policeStation);
    }

    /**
     * Updating the police stations
     *
     * @param policeStation the police station
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updatePoliceStation(@RequestBody PoliceStation policeStation) {
        LOG.info("updating the police stations");
        return policeStationService.updatePoliceStation(policeStation);
    }

    /**
     * deleting the police station
     *
     * @param policeStationList the police station list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePoliceStation(@RequestBody List<PoliceStation> policeStationList) {
        LOG.info("deleting the police stations");
        return policeStationService.deletePoliceStation(policeStationList);
    }

    /**
     * Find police stations by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findPoliceStationsByDate(@RequestParam java.sql.Date date) {
        LOG.info("Listing all the police stations by date");
        return policeStationService.findPoliceStationsByDate(date);
    }
}
