package com.app.epolice.service;

import com.app.epolice.controller.UserController;
import com.app.epolice.model.entity.policestation.InvestigationTeam;
import com.app.epolice.repository.InvestigationTeamRepository;
import com.app.epolice.util.DateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Investigation team service.
 */
@Service
public class InvestigationTeamService {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

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
     * @return response entity
     */
    public ResponseEntity<Object> listAllInvestigationTeams() {
        try {
            List<InvestigationTeam> investigationTeamList = investigationTeamRepository.findAllByActiveTrueOrderByCreatedDateDesc();
            if (investigationTeamList.isEmpty()) {
                return new ResponseEntity<>("There are no Investigation teams in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(investigationTeamList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing the list of Investigation teams in the database
     *
     * @param investigationTeamList the investigation team list
     * @return response entity
     */
    public ResponseEntity<Object> addNewInvestigationTeams(List<InvestigationTeam> investigationTeamList) {
        try {
            if (investigationTeamList.isEmpty()) {
                return new ResponseEntity<>("You are entering empty list", HttpStatus.BAD_REQUEST);
            } else {
                for (InvestigationTeam investigationTeam:investigationTeamList
                ) {
                    investigationTeam.setDeadlineDate(DateTime.getSqlDate());
                    investigationTeam.setCreatedDate(DateTime.getDateTime());
                    investigationTeam.setActive(true);
                    investigationTeamRepository.save(investigationTeam);
                }
                if(investigationTeamList.size()==1){
                    return new ResponseEntity<>("Investigation team is successfully added", HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Investigation teams are successfully added", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is deleting the InvestigationTeams from the database
     *
     * @param investigationTeamList the investigation team list
     * @return response entity
     */
    public ResponseEntity<Object> deleteInvestigationTeam(List<InvestigationTeam> investigationTeamList){
        try{
            if(investigationTeamList.isEmpty()){
                return new ResponseEntity<>("No investigation team is selected for the deletion",HttpStatus.BAD_REQUEST);
            }else{
                for (InvestigationTeam investigationTeam:investigationTeamList
                ) {
                    investigationTeam.setActive(false);
                    investigationTeam.setUpdatedDate(DateTime.getDateTime());
                    investigationTeamRepository.save(investigationTeam);
                }
                if(investigationTeamList.size()==1){
                    return new ResponseEntity<>("Investigation team is successfully deleted",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Investigation teams are successfully deleted",HttpStatus.OK);
                }
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is updating the investigation team in the database.
     *
     * @param investigationTeam the investigation team
     * @return response entity
     */
    public ResponseEntity<Object> updateInvestigationTeam(InvestigationTeam investigationTeam){
        try{
            if(null==investigationTeam){
                return new ResponseEntity<>("Null object passed in the body",HttpStatus.BAD_REQUEST);
            }else{
                investigationTeam.setUpdatedDate(DateTime.getDateTime());
                investigationTeamRepository.save(investigationTeam);
                return new ResponseEntity<>("Investigation team is successfully updated.", HttpStatus.OK);
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find all investigation teams by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    public ResponseEntity<Object> findAllInvestigationTeamsByDate(java.sql.Date date) {
        try {
            List<InvestigationTeam> investigationTeamList = investigationTeamRepository.findAllInvestigationTeamsByDate(date);
            if (investigationTeamList.isEmpty()) {
                return new ResponseEntity<>("There are no investigation teams in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(investigationTeamList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
