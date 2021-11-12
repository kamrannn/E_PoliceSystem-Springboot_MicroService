package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.CrimeType;
import com.app.epolice.service.CrimeTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
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
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

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
     * Authorizing the token
     *
     * @param token the token
     * @return boolean
     * @author "Kamran"
     */
    public boolean authorization(String token) {
        LOG.info("Authorizing the user ");
        return CrimeTypeController.token.equals(token);
    }

    /**
     * if the user is un-authorized
     *
     * @return response entity
     * @author "Kamran"
     */
    public ResponseEntity<Object> unAuthorizeUser() {
        LOG.info("Unauthorized user is trying to get access");
        return new ResponseEntity<>("Kindly do the authorization first", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Showing all the crimeTypes
     *
     * @param token the token
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfCrimeTypes(@RequestHeader("Authorization") String token){
        if (authorization(token)) {
            LOG.info("listing the crime types");
            return crimeTypeService.listAllCrimeTypes();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding the crimeTypes
     *
     * @param token     the token
     * @param crimeType the crime type
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addCrimeType(@RequestHeader("Authorization") String token,@Valid @RequestBody List<CrimeType> crimeType){
        if (authorization(token)) {
            LOG.info("adding the crime types");
            return crimeTypeService.addNewCrimeTypes(crimeType);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Updating the crimeTypes
     *
     * @param token     the token
     * @param crimeType the crime type
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateCrimeType(@RequestHeader("Authorization") String token, @RequestBody CrimeType crimeType){
        if (authorization(token)) {
            LOG.info("updating the crime types");
            return crimeTypeService.updateCrimeType(crimeType);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * deleting the crimeTypes
     *
     * @param token         the token
     * @param crimeTypeList the crime type list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCrimeType(@RequestHeader("Authorization") String token, @RequestBody List<CrimeType> crimeTypeList){
        if (authorization(token)) {
            LOG.info("updating the crime types");
            return crimeTypeService.deleteCrimeType(crimeTypeList);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Find all crime types by date response entity.
     *
     * @param token the token
     * @param date  the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findAllCrimeTypesByDate(@RequestHeader("Authorization") String token, @RequestParam java.sql.Date date) {
        if (authorization(token)) {
            LOG.info("Listing all the crime types by date");
            return crimeTypeService.findAllCrimeTypesByDate(date);
        } else {
            return unAuthorizeUser();
        }
    }
}
