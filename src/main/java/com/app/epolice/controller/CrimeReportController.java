package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.service.CrimeReportService;
import com.app.epolice.util.FileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
     * Showing all the crimeReports
     *
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfCrimeReports() {
        LOG.info("listing all the crime reports");
        return crimeReportService.listAllCrimeReports();
    }

    /**
     * Adding the crimeReports
     *
     * @param crimeReport the crime report
     * @return response entity
     */
    @PostMapping("/add_multiple_reports")
    public ResponseEntity<Object> addListOfCrimeReports(@RequestBody List<CrimeReport> crimeReport) {
        LOG.info("adding the crime reports");
        return crimeReportService.addMultipleCrimeReports(crimeReport);
    }

    /**
     * Adding a single report with multiple pictures
     *
     * @param report the report
     * @param file   the file
     * @return response entity
     */
    @PostMapping("/upload_singe_report")
    public ResponseEntity<Object> uploadReport(@Valid CrimeReport report, @RequestParam("files") MultipartFile[] file) {
        LOG.info("adding single crime report");
        return crimeReportService.addSingleCrimeReport(report, file);
    }

    /**
     * Upload a file controller (Testing)
     *
     * @param file the file
     * @return string
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String reportPictureName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uploadDir = "F:\\Development\\E-Police Project\\Images\\" + 1;
        FileUpload.saveFile(uploadDir, reportPictureName, file);
        return null;
    }

    /**
     * Updating the crimeReports
     *
     * @param crimeReport the crime report
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateCrimeReport(@RequestBody CrimeReport crimeReport) {
        LOG.info("updating single crime report");
        return crimeReportService.updateCrimeReport(crimeReport);
    }

    /**
     * deleting the crimeReports
     *
     * @param crimeReportList the crime report list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCrimeReport(@RequestBody List<CrimeReport> crimeReportList) {
        LOG.info("deleting multiple crime reports");
        return crimeReportService.deleteCrimeReport(crimeReportList);
    }

    /**
     * verifying the crimeReports
     *
     * @param status          the status
     * @param crimeReportId   the crime report id
     * @param policeStationId the police station id
     * @return response entity
     */
    @PostMapping("/verify")
    public ResponseEntity<Object> verifyCrimeReport(@RequestHeader String status, @RequestHeader long crimeReportId, @RequestHeader long policeStationId) {
        LOG.info("verifying and assigning crime reports to a specific police station");
        return crimeReportService.verifyReport(status, crimeReportId, policeStationId);
    }

    /**
     * Find all crime reports by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findAllCrimeReportsByDate(@RequestParam java.sql.Date date) {
        LOG.info("Listing all the crime reports by date");
        return crimeReportService.findAllCrimeReportsByDate(date);
    }
}
