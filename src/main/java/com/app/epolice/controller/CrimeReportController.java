package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.service.CrimeReportService;
import com.app.epolice.util.FileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * The type Crime report controller.
 */
@EnableSwagger2
@RestController
@Validated
@RequestMapping("/crime-reports")
public class CrimeReportController {
    private static final Logger LOG = LogManager.getLogger(CrimeReportController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    /**
     * Initializing the objects
     */
    CrimeReportService crimeReportService;

    /**
     * Instantiates a new Crime report controller.
     *
     * @param crimeReportService the crime report service
     */
    public CrimeReportController(CrimeReportService crimeReportService) {
        this.crimeReportService = crimeReportService;
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
        return CrimeReportController.token.equals(token);
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
     * Showing all the crimeReports
     *
     * @param token the token
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfCrimeReports(@RequestHeader("Authorization") String token){
        if (authorization(token)) {
            LOG.info("listing all the crime reports");
            return crimeReportService.listAllCrimeReports();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding the crimeReports
     *
     * @param token       the token
     * @param crimeReport the crime report
     * @return response entity
     */
    @PostMapping("/add_multiple_reports")
    public ResponseEntity<Object> addListOfCrimeReports(@RequestHeader("Authorization") String token, @RequestBody List<CrimeReport> crimeReport){
        if (authorization(token)) {
            LOG.info("adding the crime reports");
            return crimeReportService.addMultipleCrimeReports(crimeReport);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding a single report with multiple pictures
     *
     * @param token  the token
     * @param report the report
     * @param file   the file
     * @return response entity
     */
    @PostMapping("/upload_singe_report")
    public ResponseEntity<Object> uploadReport(@RequestHeader("Authorization") String token, @Valid CrimeReport report, @RequestParam("files") MultipartFile[] file) {
        if (authorization(token)) {
            LOG.info("adding single crime report");
            return crimeReportService.addSingleCrimeReport(report,file);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Upload a file controller (Testing)
     *
     * @param file  the file
     * @return string
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String reportPictureName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uploadDir = "F:\\Development\\E-Police Project\\Images\\" + 1;
        FileUpload.saveFile(uploadDir,reportPictureName, file);
        return null;
    }

    /**
     * Updating the crimeReports
     *
     * @param token       the token
     * @param crimeReport the crime report
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateCrimeReport(@RequestHeader("Authorization") String token, @RequestBody CrimeReport crimeReport){
        if (authorization(token)) {
            LOG.info("updating single crime report");
            return crimeReportService.updateCrimeReport(crimeReport);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * deleting the crimeReports
     *
     * @param token           the token
     * @param crimeReportList the crime report list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCrimeReport(@RequestHeader("Authorization") String token, @RequestBody List<CrimeReport> crimeReportList){
        if (authorization(token)) {
            LOG.info("deleting multiple crime reports");
            return crimeReportService.deleteCrimeReport(crimeReportList);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * verifying the crimeReports
     *
     * @param token           the token
     * @param status          the status
     * @param crimeReportId   the crime report id
     * @param policeStationId the police station id
     * @return response entity
     */
    @PostMapping("/verify")
    public ResponseEntity<Object> verifyCrimeReport(@RequestHeader("Authorization") String token, @RequestHeader String status,@RequestHeader long crimeReportId,@RequestHeader long policeStationId){
        if (authorization(token)) {
            LOG.info("verifying and assigning crime reports to a specific police station");
            return crimeReportService.verifyReport(status,crimeReportId,policeStationId);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Find all crime reports by date response entity.
     *
     * @param token the token
     * @param date  the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findAllCrimeReportsByDate(@RequestHeader("Authorization") String token, @RequestParam java.sql.Date date) {
        if (authorization(token)) {
            LOG.info("Listing all the crime reports by date");
            return crimeReportService.findAllCrimeReportsByDate(date);
        } else {
            return unAuthorizeUser();
        }
    }
}
