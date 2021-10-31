package com.app.epolice.controller;

import com.app.epolice.model.entity.user.User;
import com.app.epolice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping("/users")
public class UserController {
    /**
     * Initializing the service constructor
     */
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This controller is listing the user
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listUsers() {
            return userService.listAllUsers();
    }

    /**
     * This controller is adding the user
     * @param user
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    /**
     * This controller is updating the user
     * @param user
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * This controller is deleting the user
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    /**
     * This method is for the login of the user
     * @param email
     * @param password
     * @return
     */
    @GetMapping("/login")
    public ResponseEntity<Object> login(@RequestParam String email, @RequestParam String password){
        return userService.loginUser(email,password);
    }

    /**
     * This method is to verify the sms and email token
     * @param id
     * @param smsToken
     * @param emailToken
     * @return
     */
    @GetMapping("/verification")
    public ResponseEntity<Object> AccountVerification(@RequestHeader Long id, @RequestHeader String emailToken, @RequestHeader String smsToken){
        return userService.AccountVerification(id,emailToken,smsToken);
    }
}
