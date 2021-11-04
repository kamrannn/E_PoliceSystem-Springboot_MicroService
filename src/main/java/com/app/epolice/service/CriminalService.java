package com.app.epolice.service;

import com.app.epolice.controller.UserController;
import com.app.epolice.model.entity.crime.Criminal;
import com.app.epolice.repository.CriminalRepository;
import com.app.epolice.service.feignclients.FeignEBankService;
import com.app.epolice.util.DateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriminalService {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    FeignEBankService feignEBankService;
    CriminalRepository criminalRepository;
    public CriminalService(CriminalRepository criminalRepository,FeignEBankService feignEBankService) {
        this.criminalRepository = criminalRepository;
        this.feignEBankService=feignEBankService;
    }

    /**
     * Fetching all the criminals from the database
     *
     * @return
     */
    public ResponseEntity<Object> listAllCriminals() {
        try {
            List<Criminal> criminalList =criminalRepository.findAllByActive(true);
            if (criminalList.isEmpty()) {
                return new ResponseEntity<>("There are no criminals in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(criminalList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing the list of criminals in the database
     * @param criminalList
     * @return
     */
    public ResponseEntity<Object> addNewCriminals(List<Criminal> criminalList) {
        try {
            if (criminalList.isEmpty()) {
                return new ResponseEntity<>("You are entering empty list", HttpStatus.BAD_REQUEST);
            } else {
                for (Criminal criminal:criminalList
                ) {
                   criminal.setCreatedDate(DateTime.getDateTime());
                   criminal.setActive(true);
                   criminalRepository.save(criminal);
                }
                if(criminalList.size()==1){
                    return new ResponseEntity<>("Criminal is successfully added", HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Criminals are successfully added", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is deleting the Criminals from the database
     * @param criminalList
     * @return
     */
    public ResponseEntity<Object> deleteCriminal(List<Criminal> criminalList){
        try{
            if(criminalList.isEmpty()){
                return new ResponseEntity<>("No criminal is selected for the deletion",HttpStatus.BAD_REQUEST);
            }else{
                for (Criminal criminal:criminalList
                ) {
                   criminal.setActive(false);
                   criminal.setUpdatedDate(DateTime.getDateTime());
                   criminalRepository.save(criminal);
                }
                if(criminalList.size()==1){
                    return new ResponseEntity<>("Criminal is successfully deleted",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Criminals are successfully deleted",HttpStatus.OK);
                }
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is updating the criminals in the database.
     * @param criminal
     * @return
     */
    public ResponseEntity<Object> updateCriminal(Criminal criminal){
        try{
            if(null==criminal){
                return new ResponseEntity<>("Null object passed in the body",HttpStatus.BAD_REQUEST);
            }else{
               criminal.setUpdatedDate(DateTime.getDateTime());
               criminalRepository.save(criminal);
                return new ResponseEntity<>("Criminal is successfully updated.", HttpStatus.OK);
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * finding the criminal by its cnic
     * @param cnic
     * @return ResponseEntity
     */
    public ResponseEntity<Object> findCriminalByCnic(String cnic){
        try {
            Optional<Criminal> criminal = criminalRepository.findByCnic(cnic);
            if(criminal.isPresent()){
                return new ResponseEntity<>(criminal,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("There is no criminal against this cnic", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * finding the criminal by its cnic, returning boolean
     * @param cnic
     * @return boolean
     */
    public boolean verifyPersonCriminalRecord(String cnic) {
        Optional<Criminal> criminal = criminalRepository.findByCnic(cnic);
        if (criminal.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checking whether feign client is working or not
     * @return
     */
    public String checkFeignCurrencyMethod(){
        return feignEBankService.checkCurrency();
    }
}
