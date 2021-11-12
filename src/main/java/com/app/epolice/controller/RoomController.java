package com.app.epolice.controller;

import com.app.epolice.model.entity.rooms.Room;
import com.app.epolice.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Room controller.
 */
@RestController
@Validated
@RequestMapping("/room")
public class RoomController {
    private static final Logger LOG = LogManager.getLogger(RoomController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";
    /**
     * The Room service.
     */
    RoomService roomService;

    /**
     * Instantiates a new Room controller.
     *
     * @param roomService the room service
     */
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Authorizing the token
     *
     * @param token the token
     * @return boolean boolean
     * @author "Kamran"
     */
    public boolean authorization(String token) {
        LOG.info("Authorizing the user ");
        return RoomController.token.equals(token);
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
     * Showing all the rooms
     *
     * @param token the token
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfRooms(@RequestHeader("Authorization") String token){
        if (authorization(token)) {
            LOG.info("Listing all the rooms");
            return roomService.listAllRooms();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Find rooms by date response entity.
     *
     * @param token the token
     * @param date  the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findRoomsByDate(@RequestHeader("Authorization") String token, @RequestParam java.sql.Date date) {
        if (authorization(token)) {
            LOG.info("Listing all the rooms by date");
            return roomService.findRoomsByDate(date);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding the rooms
     *
     * @param token the token
     * @param rooms the rooms
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addRoom(@RequestHeader("Authorization") String token,@Valid @RequestBody List<Room> rooms){
        if (authorization(token)) {
            LOG.info("Adding new rooms");
            return roomService.addNewRooms(rooms);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Updating the rooms
     *
     * @param token the token
     * @param room  the room
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateRoom(@RequestHeader("Authorization") String token, @RequestBody Room room){
        if (authorization(token)) {
            LOG.info("Updating existing rooms");
            return roomService.updateRoom(room);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * deleting the rooms
     *
     * @param token    the token
     * @param roomList the room list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRoom(@RequestHeader("Authorization") String token, @RequestBody List<Room> roomList){
        if (authorization(token)) {
            LOG.info("deleting existing rooms");
            return roomService.deleteRoom(roomList);
        } else {
            return unAuthorizeUser();
        }

    }
}
