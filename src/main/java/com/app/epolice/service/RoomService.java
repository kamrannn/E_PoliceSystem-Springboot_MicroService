package com.app.epolice.service;

import com.app.epolice.model.entity.rooms.Room;
import com.app.epolice.repository.RoomRepository;
import com.app.epolice.util.DateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private static final Logger LOG = LogManager.getLogger(RoomService.class);
    RoomRepository roomRepository;
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Fetching all the rooms from the database
     *
     * @return list of rooms
     */
    public ResponseEntity<Object> listAllRooms() {
        try {
            List<Room> roomList = roomRepository.findAllByActiveTrueOrderByCreatedDateDesc();
            if (roomList.isEmpty()) {
                return new ResponseEntity<>("There are no rooms in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(roomList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is storing the list of rooms in the database
     *
     * @param roomList adding list of rooms
     * @return response entity
     */
    public ResponseEntity<Object> addNewRooms(List<Room> roomList) {
        try {
            if (roomList.isEmpty()) {
                return new ResponseEntity<>("You are entering empty list", HttpStatus.BAD_REQUEST);
            } else {
                for (Room room:roomList
                ) {
                    room.setCreatedDate(DateTime.getDateTime());
                    room.setActive(true);
                    roomRepository.save(room);
                }
                if(roomList.size()==1){
                    return new ResponseEntity<>("Room is successfully added", HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Rooms are successfully added", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is deleting the Rooms from the database
     *
     * @param roomList the room list
     * @return response entity
     */
    public ResponseEntity<Object> deleteRoom(List<Room> roomList){
        try{
            if(roomList.isEmpty()){
                return new ResponseEntity<>("No Room is selected for the deletion",HttpStatus.BAD_REQUEST);
            }else{
                for (Room room:roomList
                ) {
                    room.setActive(false);
                    room.setUpdatedDate(DateTime.getDateTime());
                    roomRepository.save(room);
                }
                if(roomList.size()==1){
                    return new ResponseEntity<>("Room is successfully deleted",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Rooms are successfully deleted",HttpStatus.OK);
                }
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service is updating the room in the database.
     *
     * @param room the room
     * @return response entity
     */
    public ResponseEntity<Object> updateRoom(Room room){
        try{
            if(null==room){
                return new ResponseEntity<>("Null object passed in the body",HttpStatus.BAD_REQUEST);
            }else{
                room.setUpdatedDate(DateTime.getDateTime());
                roomRepository.save(room);
                return new ResponseEntity<>("Room is successfully updated.", HttpStatus.OK);
            }
        }catch (Exception e){
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is fetching all the rooms for a specific date
     *
     * @param date the date
     * @return response entity
     */
    public ResponseEntity<Object> findRoomsByDate(java.sql.Date date) {
        try {
            List<Room> roomList = roomRepository.findAllRoomsByDate(date);
            if (roomList.isEmpty()) {
                return new ResponseEntity<>("There are no rooms in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(roomList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
