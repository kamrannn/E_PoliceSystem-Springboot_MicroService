package com.app.epolice.service;

import com.app.epolice.controller.UserController;
import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.repository.CrimeReportRepository;
import com.app.epolice.util.DateTime;
import com.app.epolice.util.FileUpload;
import com.app.epolice.util.UuidGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class CrimeReportService {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

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
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing the list of crime reports in the database
     * @param crimeReportList
     * @return
     */
    public ResponseEntity<Object> addMultipleCrimeReports(List<CrimeReport> crimeReportList) {
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
                    return new ResponseEntity<>("Crime Report is successfully added", HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Crime Reports are successfully added", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing single crime report in the database
     * @param crimeReport
     * @return
     */
    public ResponseEntity<Object> addSingleCrimeReport(CrimeReport crimeReport, MultipartFile[] multipartFileList) {
        try {
            if (null == crimeReport) {
                return new ResponseEntity<>("You are entering report", HttpStatus.BAD_REQUEST);
            } else {
                String reportUuid= UuidGenerator.getUuid();
                for (MultipartFile file:multipartFileList
                     ) {
                    String reportPictureName = StringUtils.cleanPath(file.getOriginalFilename());
                    String uploadDir = "F:\\Development\\E-Police Project\\Images\\" +reportUuid ;
                    FileUpload.saveFile(uploadDir,reportPictureName, file);
                }
                crimeReport.setUuid(reportUuid);
                crimeReport.setCreatedDate(DateTime.getDateTime());
                crimeReport.setActive(true);
                crimeReport.setStatus("Pending");
                crimeReportRepository.save(crimeReport);
                return new ResponseEntity<>("Crime Report is successfully added", HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
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
                return new ResponseEntity<>("No Crime Report is selected for the deletion",HttpStatus.BAD_REQUEST);
            }else{
                for (CrimeReport crimeReport:crimeReportList
                ) {
                    crimeReport.setActive(false);
                    crimeReport.setUpdatedDate(DateTime.getDateTime());
                    crimeReportRepository.save(crimeReport);
                }
                if(crimeReportList.size()==1){
                    return new ResponseEntity<>("Crime Report is successfully deleted",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Crime Reports are successfully deleted",HttpStatus.OK);
                }
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
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
                return new ResponseEntity<>("Crime Report is successfully updated.", HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Approving the crime Report
     * @param crimeReport
     * @return
     * @throws ParseException
     */
    public ResponseEntity<Object> verifyReport(String status,CrimeReport crimeReport) throws ParseException {
        try{
            Optional<CrimeReport> report = crimeReportRepository.findById(crimeReport.getId());
            if(report.isPresent()){
                crimeReport.setStatus(status);
                crimeReport.setUpdatedDate(DateTime.getDateTime());
                crimeReportRepository.save(crimeReport);
                return new ResponseEntity<>("The crime report is "+status, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("There is no report against this crime Id", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
