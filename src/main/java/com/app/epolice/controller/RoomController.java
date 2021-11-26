package com.app.epolice.controller;

import com.app.epolice.model.entity.rooms.Room;
import com.app.epolice.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
     * Showing all the rooms
     *
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfRooms() {
        LOG.info("Listing all the rooms");
        return roomService.listAllRooms();
    }

    /**
     * Find rooms by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findRoomsByDate(@RequestParam java.sql.Date date) {
        LOG.info("Listing all the rooms by date");
        return roomService.findRoomsByDate(date);
    }

    /**
     * Adding the rooms
     *
     * @param rooms the rooms
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addRoom(@Valid @RequestBody List<Room> rooms) {
        LOG.info("Adding new rooms");
        return roomService.addNewRooms(rooms);
    }

    /**
     * Updating the rooms
     *
     * @param room the room
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateRoom(@RequestBody Room room) {
        LOG.info("Updating existing rooms");
        return roomService.updateRoom(room);
    }

    /**
     * deleting the rooms
     *
     * @param roomList the room list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRoom(@RequestBody List<Room> roomList) {
        LOG.info("deleting existing rooms");
        return roomService.deleteRoom(roomList);
    }
}
