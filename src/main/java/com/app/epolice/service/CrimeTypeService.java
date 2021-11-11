package com.app.epolice.service;

import com.app.epolice.controller.UserController;
import com.app.epolice.model.entity.crime.CrimeType;
import com.app.epolice.model.entity.crime.Criminal;
import com.app.epolice.repository.CrimeTypeRepository;
import com.app.epolice.util.DateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrimeTypeService {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    /**
     * Initializing the crimeType Repository
     */
    CrimeTypeRepository crimeTypeRepository;
    public CrimeTypeService(CrimeTypeRepository crimeTypeRepository) {
        this.crimeTypeRepository = crimeTypeRepository;
    }

    /**
     * Fetching all the crime types from the database
     *
     * @return
     */
    public ResponseEntity<Object> listAllCrimeTypes() {
        try {
            List<CrimeType> crimeTypeList = crimeTypeRepository.findAllByActiveTrueOrderByCreatedDateDesc();
            if (crimeTypeList.isEmpty()) {
                return new ResponseEntity<>("There are no crime types in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(crimeTypeList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing the list of crime types in the database
     * @param crimeTypeList
     * @return
     */
    public ResponseEntity<Object> addNewCrimeTypes(List<CrimeType> crimeTypeList) {
        try {
            if (crimeTypeList.isEmpty()) {
                return new ResponseEntity<>("You are entering empty list", HttpStatus.BAD_REQUEST);
            } else {
                for (CrimeType crimeType:crimeTypeList
                ) {
                    crimeType.setCreatedDate(DateTime.getDateTime());
                    crimeType.setActive(true);
                    crimeTypeRepository.save(crimeType);
                }
                if(crimeTypeList.size()==1){
                    return new ResponseEntity<>("Crime Type is successfully added", HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Crime Types are successfully added", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is deleting the CrimeTypes from the database
     * @param crimeTypeList
     * @return
     */
    public ResponseEntity<Object> deleteCrimeType(List<CrimeType> crimeTypeList){
        try{
            if(crimeTypeList.isEmpty()){
                return new ResponseEntity<>("No Crime Type is selected for the deletion",HttpStatus.BAD_REQUEST);
            }else{
                for (CrimeType crimeType:crimeTypeList
                ) {
                    crimeType.setActive(false);
                    crimeType.setUpdatedDate(DateTime.getDateTime());
                    crimeTypeRepository.save(crimeType);
                }
                if(crimeTypeList.size()==1){
                    return new ResponseEntity<>("Crime Type is successfully deleted",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Crime Types are successfully deleted",HttpStatus.OK);
                }
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is updating the crime types in the database.
     * @param crimeType
     * @return
     */
    public ResponseEntity<Object> updateCrimeType(CrimeType crimeType){
        try{
            if(null==crimeType){
                return new ResponseEntity<>("Null object passed in the body",HttpStatus.BAD_REQUEST);
            }else{
                crimeType.setUpdatedDate(DateTime.getDateTime());
                crimeTypeRepository.save(crimeType);
                return new ResponseEntity<>("Crime Type is successfully updated.", HttpStatus.OK);
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find all crime types by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    public ResponseEntity<Object> findAllCrimeTypesByDate(java.sql.Date date) {
        try {
            List<CrimeType> crimeTypeList = crimeTypeRepository.findAllCrimeTypesByDate(date);
            if (crimeTypeList.isEmpty()) {
                return new ResponseEntity<>("There are no crime types in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(crimeTypeList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
