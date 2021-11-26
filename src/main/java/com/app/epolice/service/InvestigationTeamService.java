package com.app.epolice.service;

import com.app.epolice.model.entity.policestation.InvestigationTeam;
import com.app.epolice.repository.InvestigationTeamRepository;
import com.app.epolice.util.DateTime;
import com.app.epolice.util.ResponseUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * The type Investigation team service.
 */
@Service
public class InvestigationTeamService {
    private static final Logger LOG = LogManager.getLogger(InvestigationTeamService.class);

    /**
     * The Investigation team repository.
     */
    InvestigationTeamRepository investigationTeamRepository;

    /**
     * Instantiates a new Investigation team service.
     *
     * @param investigationTeamRepository the investigation team repository
     */
    public InvestigationTeamService(InvestigationTeamRepository investigationTeamRepository) {
        this.investigationTeamRepository = investigationTeamRepository;
    }

    /**
     * Fetching all the Investigation teams from the database
     *
     * @param httpServletRequest the http servlet request
     * @return response entity
     * @throws ParseException the parse exception
     */
    public ResponseEntity<Object> listAllInvestigationTeams(HttpServletRequest httpServletRequest) throws ParseException {
        try {
            List<InvestigationTeam> investigationTeamList = investigationTeamRepository.findAllByActiveTrueOrderByCreatedDateDesc();
            if (investigationTeamList.isEmpty()) {
                return new ResponseEntity<>(ResponseUtility.getResponse("There are no investigation teams in the database", null, httpServletRequest.getRequestURI(), HttpStatus.OK), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ResponseUtility.getResponse("Success", investigationTeamList, httpServletRequest.getRequestURI(), HttpStatus.OK), HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: {}", e.getMessage());
            return new ResponseEntity<>(ResponseUtility.getResponse(e.getMessage(), null, httpServletRequest.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing the list of Investigation teams in the database
     *
     * @param investigationTeamList the investigation team list
     * @param httpServletRequest    the http servlet request
     * @return response entity
     */
    public ResponseEntity<Object> addNewInvestigationTeams(List<InvestigationTeam> investigationTeamList, HttpServletRequest httpServletRequest) {
        try {
            if (investigationTeamList.isEmpty()) {
                return new ResponseEntity<>("You are entering empty list", HttpStatus.BAD_REQUEST);
            } else {
                for (InvestigationTeam investigationTeam : investigationTeamList
                ) {
                    investigationTeam.setDeadlineDate(DateTime.getSqlDate());
                    investigationTeam.setCreatedDate(DateTime.getDateTime());
                    investigationTeam.setActive(true);
                    investigationTeamRepository.save(investigationTeam);
                }
                if (investigationTeamList.size() == 1) {
                    return new ResponseEntity<>("Investigation team is successfully added", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Investigation teams are successfully added", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is deleting the InvestigationTeams from the database
     *
     * @param investigationTeamList the investigation team list
     * @param httpServletRequest    the http servlet request
     * @return response entity
     */
    public ResponseEntity<Object> deleteInvestigationTeam(List<InvestigationTeam> investigationTeamList, HttpServletRequest httpServletRequest) {
        try {
            if (investigationTeamList.isEmpty()) {
                return new ResponseEntity<>("No investigation team is selected for the deletion", HttpStatus.BAD_REQUEST);
            } else {
                for (InvestigationTeam investigationTeam : investigationTeamList
                ) {
                    investigationTeam.setActive(false);
                    investigationTeam.setUpdatedDate(DateTime.getDateTime());
                    investigationTeamRepository.save(investigationTeam);
                }
                if (investigationTeamList.size() == 1) {
                    return new ResponseEntity<>("Investigation team is successfully deleted", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Investigation teams are successfully deleted", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is updating the investigation team in the database.
     *
     * @param investigationTeam  the investigation team
     * @param httpServletRequest the http servlet request
     * @return response entity
     */
    public ResponseEntity<Object> updateInvestigationTeam(InvestigationTeam investigationTeam, HttpServletRequest httpServletRequest) {
        try {
            if (null == investigationTeam) {
                return new ResponseEntity<>("Null object passed in the body", HttpStatus.BAD_REQUEST);
            } else {
                investigationTeam.setUpdatedDate(DateTime.getDateTime());
                investigationTeamRepository.save(investigationTeam);
                return new ResponseEntity<>("Investigation team is successfully updated.", HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find all investigation teams by date response entity.
     *
     * @param date               the date
     * @param httpServletRequest the http servlet request
     * @return the response entity
     */
    public ResponseEntity<Object> findAllInvestigationTeamsByDate(Date date, HttpServletRequest httpServletRequest) {
        try {
            List<InvestigationTeam> investigationTeamList = investigationTeamRepository.findAllInvestigationTeamsByDate(date);
            if (investigationTeamList.isEmpty()) {
                return new ResponseEntity<>("There are no investigation teams in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(investigationTeamList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception" + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find investigation team by id response entity.
     *
     * @param id                 the id
     * @param httpServletRequest the http servlet request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    public ResponseEntity<Object> findInvestigationTeamById(Long id, HttpServletRequest httpServletRequest) throws ParseException {
        try {
            Optional<InvestigationTeam> investigationTeam = investigationTeamRepository.findById(id);
            if (investigationTeam.isEmpty()) {
                return new ResponseEntity<>(ResponseUtility.getResponse("There is no investigation team against the given investigation team id ", null, httpServletRequest.getRequestURI(), HttpStatus.OK), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ResponseUtility.getResponse("Success", investigationTeam, httpServletRequest.getRequestURI(), HttpStatus.OK), HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: " + e.getMessage());
            return new ResponseEntity<>(ResponseUtility.getResponse(e.getMessage(), null, httpServletRequest.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
