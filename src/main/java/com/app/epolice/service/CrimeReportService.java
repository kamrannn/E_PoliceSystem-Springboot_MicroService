package com.app.epolice.service;

import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.repository.CrimeReportRepository;
import com.app.epolice.util.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CrimeReportService {
    /**
     * Initializing the objects
     */
    CrimeReportRepository crimeReportRepository;
    public CrimeReportService(CrimeReportRepository crimeReportRepository) {
        this.crimeReportRepository = crimeReportRepository;
    }
    /**
     * Fetching all the crime reports from the database
     *
     * @return
     */
    public ResponseEntity<Object> listAllCrimeReports() {
        try {
            List<CrimeReport> crimeReportList = crimeReportRepository.findAllByActive(true);
            if (crimeReportList.isEmpty()) {
                return new ResponseEntity<>("There are no crime reports in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(crimeReportList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing the list of crime reports in the database
     * @param crimeReportList
     * @return
     */
    public ResponseEntity<Object> addNewCrimeReports(List<CrimeReport> crimeReportList) {
        try {
            if (crimeReportList.isEmpty()) {
                return new ResponseEntity<>("You are entering empty list", HttpStatus.BAD_REQUEST);
            } else {
                for (CrimeReport crimeReport:crimeReportList
                ) {
                    crimeReport.setCreatedDate(DateTime.getDateTime());
                    crimeReport.setActive(true);
                    crimeReportRepository.save(crimeReport);
                }
                if(crimeReportList.size()==1){
                    return new ResponseEntity<>("Police station is successfully added", HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Police stations are successfully added", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is deleting the CrimeReports from the database
     * @param crimeReportList
     * @return
     */
    public ResponseEntity<Object> deleteCrimeReport(List<CrimeReport> crimeReportList){
        try{
            if(crimeReportList.isEmpty()){
                return new ResponseEntity<>("No police station is selected for the deletion",HttpStatus.BAD_REQUEST);
            }else{
                for (CrimeReport crimeReport:crimeReportList
                ) {
                    crimeReport.setActive(false);
                    crimeReport.setUpdatedDate(DateTime.getDateTime());
                    crimeReportRepository.save(crimeReport);
                }
                if(crimeReportList.size()==1){
                    return new ResponseEntity<>("Police station is successfully deleted",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Police stations are successfully deleted",HttpStatus.OK);
                }
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is updating the crime reports in the database.
     * @param crimeReport
     * @return
     */
    public ResponseEntity<Object> updateCrimeReport(CrimeReport crimeReport){
        try{
            if(null==crimeReport){
                return new ResponseEntity<>("Null object passed in the body",HttpStatus.BAD_REQUEST);
            }else{
                crimeReport.setUpdatedDate(DateTime.getDateTime());
                crimeReportRepository.save(crimeReport);
                return new ResponseEntity<>("Police station is successfully updated.", HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
