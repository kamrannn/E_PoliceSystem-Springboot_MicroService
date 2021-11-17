package com.app.epolice.controller;

import com.app.epolice.config.JwtTokenUtil;
import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.model.entity.jwt.JwtRequest;
import com.app.epolice.model.entity.jwt.JwtResponse;
import com.app.epolice.model.entity.user.User;
import com.app.epolice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    /**
     * Instantiates a new controller.
     *
     * @param userService           the user service
     * @param authenticationManager the authentication manager
     * @param jwtTokenUtil          the jwt token util
     */
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * This controller is listing the active users from the database
     *
     * @return response entity
     */
    @GetMapping("/list")
    public ResponseEntity<Object> listUsers() {
            LOG.info("Listing all the users");
            return userService.listAllActiveUsers();
    }

    /**
     * This controller is listing all inactive user from the database
     *
     * @param token the token
     * @return response entity
     */
    @GetMapping("/list/inactive")
    public ResponseEntity<Object> listOfInActiveUsers(@RequestHeader("Authorization") String token) {
            LOG.info("Listing all the users that are not active");
            return userService.listOfInActiveUsers();
    }

    /**
     * This controller is listing the active users from the database
     *
     * @param date the date
     * @return response entity
     */
    @GetMapping("/by-date")
    public ResponseEntity<Object> findUsersByDate(@RequestParam java.sql.Date date) {
            LOG.info("Listing all the users by date");
            return userService.findUsersByDate(date);
    }

    /**
     * This controller is adding the user
     *
     * @param user the user
     * @return response entity
     */
    @PostMapping("/signup")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
            LOG.info("Adding the user");
            return userService.addUser(user);
    }

    /**
     * This controller is updating the user
     *
     * @param user the user
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
            LOG.info("Updating the user");
            return userService.updateUser(user);
    }

    /**
     * This controller is deleting the user
     *
     * @param id the id
     * @return response entity
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
            LOG.info("Deleting the user from the database");
            return userService.deleteUser(id);
    }

    /**
     * This method is for the login of the user
     *
     * @param email    the email
     * @param password the password
     * @return response entity
     */
    @GetMapping("/login")
    public ResponseEntity<Object> login(@RequestParam String email, @RequestParam String password) {
            LOG.info("User is trying to login the system");
            return userService.loginUser(email, password);
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
    public ResponseEntity<Object> AccountVerification(@RequestHeader Long id, @RequestHeader String emailToken, @RequestHeader String smsToken) {
            LOG.info("Doing the account verification through tokens");
            return userService.AccountVerification(id, emailToken, smsToken);
    }

    /**
     * resending the the verification token
     *
     * @param user the user
     * @return response entity
     */
    @PostMapping("/resend-verification-token")
    public ResponseEntity<Object> resendVerificationToken(@RequestBody User user ){
            LOG.info("Resending the verification tokens");
            return userService.resendVerificationToken(user.getEmail());
    }

    /**
     * Adding a single report with multiple pictures
     *
     * @param token  the token
     * @param id     the id
     * @param report the report
     * @param file   the file
     * @return response entity
     */
    @PostMapping("/upload_single_report")
    public ResponseEntity<Object> uploadReport(@RequestHeader("Authorization") String token,@RequestHeader long id, CrimeReport report, @RequestParam("files") MultipartFile[] file) {
            LOG.info("Uploading the single crime report having images attached");
            return userService.createCrimeReport(id,report,file);
    }

    /**
     * View roles of specific user response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @GetMapping("/view-roles")
    public ResponseEntity<Object> viewRolesOfSpecificUser(@RequestHeader Long userId){
            LOG.info("checking the roles of a specific user having id: "+userId);
            return userService.specificUserRoles(userId);
    }

    /**
     * View specific user department response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @GetMapping("/view-department")
    public ResponseEntity<Object> viewSpecificUserDepartment(@RequestHeader Long userId){
            LOG.info("checking the department of a specific user having id: "+userId);
            return userService.specificUserDepartment(userId);

    }

    /**
     * Create authentication token response entity.
     *
     * @param authenticationRequest the authentication request
     * @return the response entity
     * @throws Exception the exception
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    /**
     * This method is getting used by /authenticate or login method
     * @param username
     * @param password
     * @throws Exception
     */
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

