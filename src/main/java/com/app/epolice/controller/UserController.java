package com.app.epolice.controller;

import com.app.epolice.model.entity.User;
import com.app.epolice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
public class UserController {
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This controller is listing the user
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> ListUsers() {
            return userService.ListAllUsers();
    }

    /**
     * This controller is adding the user
     * @param user
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Object> AddUser(User user){
        return userService.AddUser(user);
    }

    /**
     * This controller is updating the user
     * @param user
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> UpdateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * This controller is deleting the user
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> DeleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
