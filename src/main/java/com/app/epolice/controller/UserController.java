package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.model.entity.user.User;
import com.app.epolice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@EnableSwagger2
@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    private static final String token = "40dc498b-e837-4fa9-8e53-c1d51e01af15";

    /**
     * Initializing the service constructor
     */
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Authorizing the token
     *
     * @param token
     * @return
     * @Author "Kamran"
     */
    public boolean authorization(String token) {
        LOG.info("Authorizing the user ");
        return UserController.token.equals(token);
    }

    /**
     * if the user is un-authorized
     *
     * @return
     * @Author "Kamran"
     */
    public ResponseEntity<Object> unAuthorizeUser() {
        LOG.info("Unauthorized user is trying to get access");
        return new ResponseEntity<>("Kindly do the authorization first", HttpStatus.UNAUTHORIZED);
    }

    /**
     * This controller is listing the active users from the database
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listUsers(@RequestHeader("Authorization") String token) {
        if (authorization(token)) {
            LOG.info("Listing all the users");
            return userService.listAllActiveUsers();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * This controller is listing all inactive user from the database
     *
     * @return
     */
    @GetMapping("/list/inactive")
    public ResponseEntity<Object> listOfInActiveUsers(@RequestHeader("Authorization") String token) {
        if (authorization(token)) {
            LOG.info("Listing all the users that are not active");
            return userService.listOfInActiveUsers();
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * This controller is adding the user
     *
     * @param user
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        LOG.info("Adding the user");
        return userService.addUser(user);
    }

    /**
     * This controller is updating the user
     *
     * @param user
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestHeader("Authorization") String token, @RequestBody User user) {
        if (authorization(token)) {
            LOG.info("Updating the user");
            return userService.updateUser(user);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * This controller is deleting the user
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        if (authorization(token)) {
            LOG.info("Deleting the user from the database");
            return userService.deleteUser(id);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * This method is for the login of the user
     *
     * @param email
     * @param password
     * @return
     */
    @GetMapping("/login")
    public ResponseEntity<Object> login(@RequestHeader("Authorization") String token, @RequestParam String email, @RequestParam String password) {
        if (authorization(token)) {
            LOG.info("User is trying to login the system");
            return userService.loginUser(email, password);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * This method is to verify the sms and email token
     *
     * @param id
     * @param smsToken
     * @param emailToken
     * @return
     */
    @GetMapping("/verification")
    public ResponseEntity<Object> AccountVerification(@RequestHeader("Authorization") String token, @RequestHeader Long id, @RequestHeader String emailToken, @RequestHeader String smsToken) {
        if (authorization(token)) {
            LOG.info("Doing the account verification through tokens");
            return userService.AccountVerification(id, emailToken, smsToken);
        } else {
            return unAuthorizeUser();
        }
    }

    /**
     * resending the the verification token
     * @param user
     * @return
     */
    @PostMapping("/resend-verification-token")
    public ResponseEntity<Object> resendVerificationToken(@RequestBody User user ){
        return userService.resendVerificationToken(user.getEmail());
    }

    /**
     * Adding a single report with multiple pictures
     * @param report
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload_singe_report")
    public ResponseEntity<Object> uploadReport(@RequestHeader long id, CrimeReport report, @RequestParam("files") MultipartFile[] file) throws IOException {
        return userService.createCrimeReport(id,report,file);
    }
}
