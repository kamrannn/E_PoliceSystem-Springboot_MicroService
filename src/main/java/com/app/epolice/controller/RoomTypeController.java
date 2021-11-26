package com.app.epolice.controller;

import com.app.epolice.model.entity.rooms.RoomType;
import com.app.epolice.service.RoomTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Room type controller.
 */
@RestController
@Validated
@RequestMapping("/role-type")
public class RoomTypeController {
    private static final Logger LOG = LogManager.getLogger(RoomTypeController.class);
    /**
     * The Room type service.
     */
    RoomTypeService roomTypeService;

    /**
     * Instantiates a new Room type controller.
     *
     * @param roomTypeService the room type service
     */
    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    /**
     * Showing all the roomTypes
     *
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfRoomTypes() {
        LOG.info("Listing all the roomTypes");
        return roomTypeService.listAllRoomTypes();
    }

    /**
     * Find room types by date response entity.
     *
     * @param date the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findRoomTypesByDate(@RequestParam java.sql.Date date) {
        LOG.info("Listing all the roomTypes by date");
        return roomTypeService.findRoomTypesByDate(date);
    }

    /**
     * Adding the roomTypes
     *
     * @param roomTypes the roomTypes
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addRoomType(@Valid @RequestBody List<RoomType> roomTypes) {
        LOG.info("Adding new roomTypes");
        return roomTypeService.addNewRoomTypes(roomTypes);
    }

    /**
     * Updating the roomTypes
     *
     * @param roomType the roomType
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateRoomType(@RequestBody RoomType roomType) {
        LOG.info("Updating existing roomTypes");
        return roomTypeService.updateRoomType(roomType);
    }

    /**
     * deleting the roomTypes
     *
     * @param roomTypeList the roomType list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRoomType(@RequestBody List<RoomType> roomTypeList) {
        LOG.info("deleting existing roomTypes");
        return roomTypeService.deleteRoomType(roomTypeList);

    }
}
