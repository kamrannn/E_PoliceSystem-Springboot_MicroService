package com.app.epolice.controller;

import com.app.epolice.model.entity.policestation.InvestigationTeam;
import com.app.epolice.service.InvestigationTeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import javax.validation.Valid;
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
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfInvestigationTeams() {
        LOG.info("Listing all the investigation Teams");
        return investigationTeamService.listAllInvestigationTeams();
    }

    /**
     * Adding the investigationTeams
     *
     * @param investigationTeam the investigation team
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addInvestigationTeam(@Valid @RequestBody List<InvestigationTeam> investigationTeam) {
        LOG.info("Adding the investigation Teams");
        return investigationTeamService.addNewInvestigationTeams(investigationTeam);
    }

    /**
     * Updating the investigationTeams
     *
     * @param investigationTeam the investigation team
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateInvestigationTeam(@RequestBody InvestigationTeam investigationTeam) {
        LOG.info("updating the investigation Teams");
        return investigationTeamService.updateInvestigationTeam(investigationTeam);
    }

    /**
     * deleting the investigationTeams
     *
     * @param investigationTeamList the investigation team list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteInvestigationTeam(@RequestBody List<InvestigationTeam> investigationTeamList) {
        LOG.info("deleting the investigation Teams");
        return investigationTeamService.deleteInvestigationTeam(investigationTeamList);
    }

    /**
     * Find all investigation teams by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findAllInvestigationTeamsByDate(@RequestParam java.sql.Date date) {
        LOG.info("Listing all the investigation teams by date");
        return investigationTeamService.findAllInvestigationTeamsByDate(date);
    }

    /**
     * Showing all the investigationTeams
     *
     * @return response entity
     */
    @GetMapping("/list/team")
    public ResponseEntity<Object> findInvestigationTeamById(@RequestParam Long id) {
        LOG.info("Listing the investigation Team by their id");
        return investigationTeamService.findInvestigationTeamById(id);
    }
}
