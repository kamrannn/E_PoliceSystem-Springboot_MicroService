package com.app.epolice.controller;

import com.app.epolice.model.entity.policestation.PoliceStation;
import com.app.epolice.service.PoliceStationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/police-station")
public class PoliceStationController {
    /**
     * Initializing the objects
     */
    PoliceStationService policeStationService;
    public PoliceStationController(PoliceStationService policeStationService) {
        this.policeStationService = policeStationService;
    }

    /**
     * Showing all the police stations
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfPoliceStations(){
        return policeStationService.listAllPoliceStations();
    }

    /**
     * Adding the police stations
     * @param policeStation
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addPoliceStation(@RequestBody List<PoliceStation> policeStation){
        return policeStationService.addNewPoliceStations(policeStation);
    }

    /**
     * Updating the police stations
     * @param policeStation
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updatePoliceStation(@RequestBody PoliceStation policeStation){
        return policeStationService.updatePoliceStation(policeStation);
    }

    /**
     * deleting the police station
     * @param policeStationList
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePoliceStation(@RequestBody List<PoliceStation> policeStationList){
        return policeStationService.deletePoliceStation(policeStationList);
    }
}
