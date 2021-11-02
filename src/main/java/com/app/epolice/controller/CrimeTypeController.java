package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.CrimeType;
import com.app.epolice.service.CrimeTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/crime-type")
public class CrimeTypeController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    /**
     * Initializing the crime type service object
     */
    CrimeTypeService crimeTypeService;
    public CrimeTypeController(CrimeTypeService crimeTypeService) {
        this.crimeTypeService = crimeTypeService;
    }

    /**
     * Showing all the crimeTypes
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfCrimeTypes(){
        return crimeTypeService.listAllCrimeTypes();
    }

    /**
     * Adding the crimeTypes
     * @param crimeType
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addCrimeType(@RequestBody List<CrimeType> crimeType){
        return crimeTypeService.addNewCrimeTypes(crimeType);
    }

    /**
     * Updating the crimeTypes
     * @param crimeType
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateCrimeType(@RequestBody CrimeType crimeType){
        return crimeTypeService.updateCrimeType(crimeType);
    }

    /**
     * deleting the crimeTypes
     * @param crimeTypeList
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCrimeType(@RequestBody List<CrimeType> crimeTypeList){
        return crimeTypeService.deleteCrimeType(crimeTypeList);
    }
}
