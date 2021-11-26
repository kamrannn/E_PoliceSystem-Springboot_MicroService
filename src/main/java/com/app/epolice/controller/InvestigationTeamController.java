package com.app.epolice.controller;

import com.app.epolice.model.entity.policestation.InvestigationTeam;
import com.app.epolice.service.InvestigationTeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

/**
 * The type Investigation team controller.
 */
@EnableSwagger2
@RestController
@Validated
@RequestMapping("/investigation-team")
public class InvestigationTeamController {
    private static final Logger LOG = LogManager.getLogger(InvestigationTeamController.class);

    /**
     * The Investigation team service.
     */
    InvestigationTeamService investigationTeamService;

    /**
     * Instantiates a new Investigation team controller.
     *
     * @param investigationTeamService the investigation team service
     */
    public InvestigationTeamController(InvestigationTeamService investigationTeamService) {
        this.investigationTeamService = investigationTeamService;
    }

    /**
     * Showing all the investigationTeams
     *
     * @param httpServletRequest the http servlet request
     * @return response entity
     * @throws ParseException the parse exception
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfInvestigationTeams(HttpServletRequest httpServletRequest) throws ParseException {
        LOG.info("Listing all the investigation Teams");
        return investigationTeamService.listAllInvestigationTeams(httpServletRequest);
    }

    /**
     * Adding the investigationTeams
     *
     * @param investigationTeam  the investigation team
     * @param httpServletRequest the http servlet request
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addInvestigationTeam(@Valid @RequestBody List<InvestigationTeam> investigationTeam, HttpServletRequest httpServletRequest) {
        LOG.info("Adding the investigation Teams");
        return investigationTeamService.addNewInvestigationTeams(investigationTeam, httpServletRequest);
    }

    /**
     * Updating the investigationTeams
     *
     * @param investigationTeam  the investigation team
     * @param httpServletRequest the http servlet request
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateInvestigationTeam(@RequestBody InvestigationTeam investigationTeam, HttpServletRequest httpServletRequest) {
        LOG.info("updating the investigation Teams");
        return investigationTeamService.updateInvestigationTeam(investigationTeam, httpServletRequest);
    }

    /**
     * deleting the investigationTeams
     *
     * @param investigationTeamList the investigation team list
     * @param httpServletRequest    the http servlet request
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteInvestigationTeam(@RequestBody List<InvestigationTeam> investigationTeamList, HttpServletRequest httpServletRequest) {
        LOG.info("deleting the investigation Teams");
        return investigationTeamService.deleteInvestigationTeam(investigationTeamList, httpServletRequest);
    }

    /**
     * Find all investigation teams by date response entity.
     *
     * @param date               the date
     * @param httpServletRequest the http servlet request
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findAllInvestigationTeamsByDate(@RequestParam java.sql.Date date, HttpServletRequest httpServletRequest) {
        LOG.info("Listing all the investigation teams by date");
        return investigationTeamService.findAllInvestigationTeamsByDate(date, httpServletRequest);
    }

    /**
     * Showing all the investigationTeams
     *
     * @param id                 the id
     * @param httpServletRequest the http servlet request
     * @return response entity
     * @throws ParseException the parse exception
     */
    @GetMapping("/list/team")
    public ResponseEntity<Object> findInvestigationTeamById(@RequestParam Long id, HttpServletRequest httpServletRequest) throws ParseException {
        LOG.info("Listing the investigation Team by their id");
        return investigationTeamService.findInvestigationTeamById(id, httpServletRequest);
    }
}
