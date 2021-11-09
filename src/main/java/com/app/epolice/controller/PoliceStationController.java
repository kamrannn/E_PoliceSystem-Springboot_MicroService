package com.app.epolice.controller;

import com.app.epolice.model.entity.policestation.PoliceStation;
import com.app.epolice.service.PoliceStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/police-stations")
public class PoliceStationController {
    private static final Logger LOG = LogManager.getLogger(PoliceStationController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    /**
     * Initializing the objects
     */
    PoliceStationService policeStationService;
    public PoliceStationController(PoliceStationService policeStationService) {
        this.policeStationService = policeStationService;
    }

    /**
     * Authorizing the token
     *
     * @param token
     * @return
     * @Author "Kamran"
     */
    public boolean authorization(String token) {
        LOG.info("Authorizing the user ");
        return PoliceStationController.token.equals(token);
    }

    /**
     * if the user is un-authorized
     *
     * @return
     * @Author "Kamran"
     */
    public ResponseEntity<Object> unAuthorizeUser() {
        LOG.info("Unauthorized user is trying to get access");
        return new ResponseEntity<>("Kindly do the authorization first", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Showing all the police stations
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfPoliceStations(@RequestHeader("Authorization") String token){
        if (authorization(token)) {
            LOG.info("Listing all the police stations");
            return policeStationService.listAllPoliceStations();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding the police stations
     * @param token
     * @param policeStation
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addPoliceStation(@RequestHeader("Authorization") String token, @RequestBody List<PoliceStation> policeStation){
        if (authorization(token)) {
            LOG.info("Listing all the police stations");
            return policeStationService.addNewPoliceStations(policeStation);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Updating the police stations
     * @param token
     * @param policeStation
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updatePoliceStation(@RequestHeader("Authorization") String token, @RequestBody PoliceStation policeStation){
        if (authorization(token)) {
            LOG.info("updating the police stations");
            return policeStationService.updatePoliceStation(policeStation);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * deleting the police station
     * @param policeStationList
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePoliceStation(@RequestHeader("Authorization") String token, @RequestBody List<PoliceStation> policeStationList){
        if (authorization(token)) {
            LOG.info("deleting the police stations");
            return policeStationService.deletePoliceStation(policeStationList);
        } else {
            return unAuthorizeUser();
        }
    }
}
