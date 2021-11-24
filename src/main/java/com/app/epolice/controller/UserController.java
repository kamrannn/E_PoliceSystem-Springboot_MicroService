package com.app.epolice.controller;

import com.app.epolice.model.entity.crime.CrimeReport;

import com.app.epolice.model.entity.user.User;
import com.app.epolice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * The type User controller.
 */
@EnableSwagger2
@RestController
@Validated
@RequestMapping("/users")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    /**
     * Initializing the service constructor
     */
    UserService userService;

    /**
     * Instantiates a new controller.
     *
     * @param userService           the user service
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This controller is listing the active users from the database
     *
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listUsers(HttpServletRequest httpServletRequest) {
            LOG.info("Listing all the users");
            return userService.listAllActiveUsers(httpServletRequest);
    }

    /**
     * This controller is listing all inactive user from the database
     *
     * @return response entity
     */
    @GetMapping("/list/inactive")
    public ResponseEntity<Object> listOfInActiveUsers(HttpServletRequest httpServletRequest) {
            LOG.info("Listing all the users that are not active");
            return userService.listOfInActiveUsers(httpServletRequest);
    }

    /**
     * This controller is listing the active users from the database
     *
     * @param date the date
     * @return response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findUsersByDate(@RequestParam java.sql.Date date, HttpServletRequest httpServletRequest) {
            LOG.info("Listing all the users by date");
            return userService.findUsersByDate(date, httpServletRequest);
    }

    /**
     * This controller is adding the user
     *
     * @param user the user
     * @return response entity
     */
    @PostMapping("/signup")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user, HttpServletRequest httpServletRequest) {
            LOG.info("Adding the user");
            return userService.addUser(user, httpServletRequest);
    }

    /**
     * This controller is updating the user
     *
     * @param user the user
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody User user, HttpServletRequest httpServletRequest) {
            LOG.info("Updating the user");
            return userService.updateUser(user,httpServletRequest);
    }

    /**
     * This controller is deleting the user
     *
     * @param id the id
     * @return response entity
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id, HttpServletRequest httpServletRequest) {
            LOG.info("Deleting the user from the database");
            return userService.deleteUser(id, httpServletRequest);
    }

    /**
     * This method is to verify the sms and email token
     *
     * @param id         the id
     * @param emailToken the email token
     * @param smsToken   the sms token
     * @return response entity
     */
    @GetMapping("/verification")
    public ResponseEntity<Object> accountVerification(@RequestHeader Long id, @RequestHeader String emailToken, @RequestHeader String smsToken, HttpServletRequest httpServletRequest) {
            LOG.info("Doing the account verification through tokens");
            return userService.AccountVerification(id, emailToken, smsToken, httpServletRequest);
    }

    /**
     * resending the the verification token
     *
     * @param user the user
     * @return response entity
     */
    @PostMapping("/resend-verification-token")
    public ResponseEntity<Object> resendVerificationToken(@RequestBody User user, HttpServletRequest httpServletRequest){
            LOG.info("Resending the verification tokens");
            return userService.resendVerificationToken(user.getEmail(), httpServletRequest);
    }

    /**
     * Adding a single report with multiple pictures
     *
     * @param id     the id
     * @param report the report
     * @param file   the file
     * @return response entity
     */
    @PostMapping("/upload_single_report")
    public ResponseEntity<Object> uploadReport(@RequestHeader long id, CrimeReport report, @RequestParam("files") MultipartFile[] file, HttpServletRequest httpServletRequest) {
            LOG.info("Uploading the single crime report having images attached");
            return userService.createCrimeReport(id,report,file, httpServletRequest);
    }

    /**
     * View roles of specific user response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @GetMapping("/view-roles")
    public ResponseEntity<Object> viewRolesOfSpecificUser(@RequestHeader Long userId, HttpServletRequest httpServletRequest){
            LOG.info("checking the roles of a specific user having id: {}", userId);
            return userService.specificUserRoles(userId,httpServletRequest);
    }

    /**
     * View specific user department response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @GetMapping("/view-department")
    public ResponseEntity<Object> viewSpecificUserDepartment(@RequestHeader Long userId, HttpServletRequest httpServletRequest){
            LOG.info("checking the department of a specific user having id: ",userId);
            return userService.specificUserDepartment(userId, httpServletRequest);

    }

    /**
     * Create authentication token response entity.
     *
     * @param authenticationRequest the authentication request
     * @return the response entity
     * @throws Exception the exception
     */
/*    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }*/

    /**
     * This method is getting used by /authenticate or login method
     * @param username
     * @param password
     * @throws Exception
     */
/*
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
*/
}

