package com.app.epolice.service;

import com.app.epolice.controller.UserController;
import com.app.epolice.model.entity.rooms.RoomType;
import com.app.epolice.repository.RoomTypeRepository;
import com.app.epolice.util.DateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    /**
     * Fetching all the roomTypes from the database
     *
     * @return list of roomTypes
     */
    public ResponseEntity<Object> listAllRoomTypes() {
        try {
            List<RoomType> roomTypeList = roomTypeRepository.findAllByActiveTrueOrderByCreatedDateDesc();
            if (roomTypeList.isEmpty()) {
                return new ResponseEntity<>("There are no roomTypes in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(roomTypeList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing the list of roomTypes in the database
     *
     * @param roomTypeList adding list of roomTypes
     * @return response entity
     */
    public ResponseEntity<Object> addNewRoomTypes(List<RoomType> roomTypeList) {
        try {
            if (roomTypeList.isEmpty()) {
                return new ResponseEntity<>("You are entering empty list", HttpStatus.BAD_REQUEST);
            } else {
                for (RoomType roomType:roomTypeList
                ) {
                    roomType.setCreatedDate(DateTime.getDateTime());
                    roomType.setActive(true);
                    roomTypeRepository.save(roomType);
                }
                if(roomTypeList.size()==1){
                    return new ResponseEntity<>("RoomType is successfully added", HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("RoomTypes are successfully added", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is deleting the RoomTypes from the database
     *
     * @param roomTypeList the roomType list
     * @return response entity
     */
    public ResponseEntity<Object> deleteRoomType(List<RoomType> roomTypeList){
        try{
            if(roomTypeList.isEmpty()){
                return new ResponseEntity<>("No RoomType is selected for the deletion",HttpStatus.BAD_REQUEST);
            }else{
                for (RoomType roomType:roomTypeList
                ) {
                    roomType.setActive(false);
                    roomType.setUpdatedDate(DateTime.getDateTime());
                    roomTypeRepository.save(roomType);
                }
                if(roomTypeList.size()==1){
                    return new ResponseEntity<>("RoomType is successfully deleted",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("RoomTypes are successfully deleted",HttpStatus.OK);
                }
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is updating the roomType in the database.
     *
     * @param roomType the roomType
     * @return response entity
     */
    public ResponseEntity<Object> updateRoomType(RoomType roomType){
        try{
            if(null==roomType){
                return new ResponseEntity<>("Null object passed in the body",HttpStatus.BAD_REQUEST);
            }else{
                roomType.setUpdatedDate(DateTime.getDateTime());
                roomTypeRepository.save(roomType);
                return new ResponseEntity<>("RoomType is successfully updated.", HttpStatus.OK);
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is fetching all the roomTypes for a specific date
     *
     * @param date the date
     * @return response entity
     */
    public ResponseEntity<Object> findRoomTypesByDate(java.sql.Date date) {
        try {
            List<RoomType> roomTypeList = roomTypeRepository.findAllRoomTypesByDate(date);
            if (roomTypeList.isEmpty()) {
                return new ResponseEntity<>("There are no roomTypes in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(roomTypeList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
