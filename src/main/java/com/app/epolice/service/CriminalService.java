package com.app.epolice.service;

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

/**
 * The type Criminal service.
 */
@Service
public class CriminalService {
    private static final Logger LOG = LogManager.getLogger(CriminalService.class);

    /**
     * The Feign e bank service.
     */
    FeignEBankService feignEBankService;
    /**
     * The Criminal repository.
     */
    CriminalRepository criminalRepository;

    /**
     * Instantiates a new Criminal service.
     *
     * @param criminalRepository the criminal repository
     * @param feignEBankService  the feign e bank service
     */
    public CriminalService(CriminalRepository criminalRepository, FeignEBankService feignEBankService) {
        this.criminalRepository = criminalRepository;
        this.feignEBankService = feignEBankService;
    }

    /**
     * Fetching all the criminals from the database
     *
     * @return response entity
     */
    public ResponseEntity<Object> listAllCriminals() {
        try {
            List<Criminal> criminalList = criminalRepository.findAllByActiveTrueOrderByCreatedDateDesc();
            if (criminalList.isEmpty()) {
                return new ResponseEntity<>("There are no criminals in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(criminalList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing the list of criminals in the database
     *
     * @param criminalList the criminal list
     * @return response entity
     */
    public ResponseEntity<Object> addNewCriminals(List<Criminal> criminalList) {
        try {
            if (criminalList.isEmpty()) {
                return new ResponseEntity<>("You are entering empty list", HttpStatus.BAD_REQUEST);
            } else {
                for (Criminal criminal : criminalList
                ) {
                    criminal.setCreatedDate(DateTime.getDateTime());
                    criminal.setActive(true);
                    criminalRepository.save(criminal);
                }
                if (criminalList.size() == 1) {
                    return new ResponseEntity<>("Criminal is successfully added", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Criminals are successfully added", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is deleting the Criminals from the database
     *
     * @param criminalList the criminal list
     * @return response entity
     */
    public ResponseEntity<Object> deleteCriminal(List<Criminal> criminalList) {
        try {
            if (criminalList.isEmpty()) {
                return new ResponseEntity<>("No criminal is selected for the deletion", HttpStatus.BAD_REQUEST);
            } else {
                for (Criminal criminal : criminalList
                ) {
                    criminal.setActive(false);
                    criminal.setUpdatedDate(DateTime.getDateTime());
                    criminalRepository.save(criminal);
                }
                if (criminalList.size() == 1) {
                    return new ResponseEntity<>("Criminal is successfully deleted", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Criminals are successfully deleted", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is updating the criminals in the database.
     *
     * @param criminal the criminal
     * @return response entity
     */
    public ResponseEntity<Object> updateCriminal(Criminal criminal) {
        try {
            if (null == criminal) {
                return new ResponseEntity<>("Null object passed in the body", HttpStatus.BAD_REQUEST);
            } else {
                criminal.setUpdatedDate(DateTime.getDateTime());
                criminalRepository.save(criminal);
                return new ResponseEntity<>("Criminal is successfully updated.", HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * finding the criminal by its cnic
     *
     * @param cnic the cnic
     * @return ResponseEntity response entity
     */
    public ResponseEntity<Object> findCriminalByCnic(String cnic) {
        try {
            Optional<Criminal> criminal = criminalRepository.findByCnic(cnic);
            if (criminal.isPresent()) {
                return new ResponseEntity<>(criminal, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no criminal against this cnic", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * finding the criminal by its cnic, returning boolean
     *
     * @param cnic the cnic
     * @return boolean boolean
     */
    public boolean verifyPersonCriminalRecord(String cnic) {
        Optional<Criminal> criminal = criminalRepository.findByCnic(cnic);
        return criminal.isPresent();
    }

    /**
     * Find all criminals by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    public ResponseEntity<Object> findAllCriminalsByDate(java.sql.Date date) {
        try {
            List<Criminal> criminalList = criminalRepository.findAllCriminalsByDate(date);
            if (criminalList.isEmpty()) {
                return new ResponseEntity<>("There are no criminals in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(criminalList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception" + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Checking whether feign client is working or not
     *
     * @return string string
     */
    public String checkFeignCurrencyMethod() {
        return feignEBankService.checkCurrency();
    }
}
