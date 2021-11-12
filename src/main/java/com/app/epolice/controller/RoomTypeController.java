package com.app.epolice.controller;

import com.app.epolice.model.entity.rooms.RoomType;
import com.app.epolice.service.RoomTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
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
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";
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
     * Authorizing the token
     *
     * @param token the token
     * @return boolean boolean
     * @author "Kamran"
     */
    public boolean authorization(String token) {
        LOG.info("Authorizing the user ");
        return RoomTypeController.token.equals(token);
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
     * Showing all the roomTypes
     *
     * @param token the token
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listOfRoomTypes(@RequestHeader("Authorization") String token){
        if (authorization(token)) {
            LOG.info("Listing all the roomTypes");
            return roomTypeService.listAllRoomTypes();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Find room types by date response entity.
     *
     * @param token the token
     * @param date  the date
     * @return the response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findRoomTypesByDate(@RequestHeader("Authorization") String token, @RequestParam java.sql.Date date) {
        if (authorization(token)) {
            LOG.info("Listing all the roomTypes by date");
            return roomTypeService.findRoomTypesByDate(date);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Adding the roomTypes
     *
     * @param token     the token
     * @param roomTypes the roomTypes
     * @return response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addRoomType(@RequestHeader("Authorization") String token,@Valid @RequestBody List<RoomType> roomTypes){
        if (authorization(token)) {
            LOG.info("Adding new roomTypes");
            return roomTypeService.addNewRoomTypes(roomTypes);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * Updating the roomTypes
     *
     * @param token    the token
     * @param roomType the roomType
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateRoomType(@RequestHeader("Authorization") String token, @RequestBody RoomType roomType){
        if (authorization(token)) {
            LOG.info("Updating existing roomTypes");
            return roomTypeService.updateRoomType(roomType);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * deleting the roomTypes
     *
     * @param token        the token
     * @param roomTypeList the roomType list
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRoomType(@RequestHeader("Authorization") String token, @RequestBody List<RoomType> roomTypeList){
        if (authorization(token)) {
            LOG.info("deleting existing roomTypes");
            return roomTypeService.deleteRoomType(roomTypeList);
        } else {
            return unAuthorizeUser();
        }

    }
}
