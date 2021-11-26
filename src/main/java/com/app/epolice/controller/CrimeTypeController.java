package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.CrimeType;
import com.app.epolice.service.CrimeTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Crime type controller.
 */
@EnableSwagger2
@RestController
@Validated
@RequestMapping("/crime-type")
public class CrimeTypeController {
    private static final Logger LOG = LogManager.getLogger(CrimeTypeController.class);

    /**
     * Initializing the crime type service object
     */
    CrimeTypeService crimeTypeService;

    /**
     * Instantiates a new Crime type controller.
     *
     * @param crimeTypeService the crime type service
     */
    public CrimeTypeController(CrimeTypeService crimeTypeService) {
        this.crimeTypeService = crimeTypeService;
    }

    /**
     * Showing all the crimeTypes
     *
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfCrimeTypes() {
        LOG.info("listing the crime types");
        return crimeTypeService.listAllCrimeTypes();
    }

    /**
     * Adding the crimeTypes
     *
     * @param crimeType the crime type
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addCrimeType(@Valid @RequestBody List<CrimeType> crimeType) {
        LOG.info("adding the crime types");
        return crimeTypeService.addNewCrimeTypes(crimeType);
    }

    /**
     * Updating the crimeTypes
     *
     * @param crimeType the crime type
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateCrimeType(@RequestBody CrimeType crimeType) {
        LOG.info("updating the crime types");
        return crimeTypeService.updateCrimeType(crimeType);
    }

    /**
     * deleting the crimeTypes
     *
     * @param crimeTypeList the crime type list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCrimeType(@RequestBody List<CrimeType> crimeTypeList) {
        LOG.info("updating the crime types");
        return crimeTypeService.deleteCrimeType(crimeTypeList);
    }

    /**
     * Find all crime types by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findAllCrimeTypesByDate(@RequestParam java.sql.Date date) {
        LOG.info("Listing all the crime types by date");
        return crimeTypeService.findAllCrimeTypesByDate(date);
    }
}
