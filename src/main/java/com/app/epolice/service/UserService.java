package com.app.epolice.service;

import com.app.epolice.controller.UserController;
import com.app.epolice.model.dto.UserDto;
import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.model.entity.user.User;
import com.app.epolice.repository.UserRepository;
import com.app.epolice.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * The type User service.
 */
@Service
public class UserService implements UserDetailsService {
    private static final Logger LOG = LogManager.getLogger(UserService.class);
    private Date tokenExpireTime = null;

    /**
     * Initializing the Repositories
     */
    final UserRepository userRepository;
    /**
     * The Email notification.
     */
    final EmailNotification emailNotification;
    /**
     * The Sms notification.
     */
    final SmsNotification smsNotification;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository    the user repository
     * @param emailNotification the email notification
     * @param smsNotification   the sms notification
     */
    public UserService(UserRepository userRepository,EmailNotification emailNotification,SmsNotification smsNotification ) {
        this.userRepository = userRepository;
        this.emailNotification = emailNotification;
        this.smsNotification = smsNotification;
    }

    /**
     * This method is fetching all the active users from the database
     *
     * @return list of all active users in the database
     */
    public ResponseEntity<Object> listAllActiveUsers(HttpServletRequest httpServletRequest) {
        try {
            List<User> userList = userRepository.findAllByActiveTrueOrderByCreatedDateDesc();
            if (userList.isEmpty()) {
                return new ResponseEntity<>(ResponseUtility.getResponse("There are no users in the database",null,httpServletRequest.getRequestURI() ), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ResponseUtility.getResponse("Success", userList ,httpServletRequest.getRequestURI()), HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage()+ e.getCause());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is fetching all the InActive users from the database
     *
     * @return response entity
     */
    public ResponseEntity<Object> listOfInActiveUsers(HttpServletRequest httpServletRequest) {
        try {
            List<User> userList = userRepository.findAllByActive(false);
            if (userList.isEmpty()) {
                return new ResponseEntity<>(ResponseUtility.getResponse("There are no users in the database",null, httpServletRequest.getRequestURI()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ResponseUtility.getResponse("Success", userList, httpServletRequest.getRequestURI()), HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is adding the user
     *
     * @param user the user
     * @return response entity
     */
    public ResponseEntity<Object> addUser(User user,HttpServletRequest httpServletRequest) {
        try {
            Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                if (existingUser.get().isActive()) {
                    return new ResponseEntity<>("User already present", HttpStatus.OK);
                } else {
                    existingUser.get().setActive(true);
                    userRepository.save(existingUser.get());
                    return new ResponseEntity<>("User is successfully added", HttpStatus.OK);
                }
            } else {
                Random rnd = new Random(); //Generating a random number
                int emailToken = rnd.nextInt(999999) + 100000; //Generating a random number of 6 digits
                emailNotification.sendMail(user.getEmail(), "Your verification code is: " + emailToken);
                user.setEmailToken(emailToken+"");

                int smsToken = rnd.nextInt(999999) + 100000;
                smsNotification.Notification(user.getPhoneNo(), "Your verification code: " + smsToken);
                user.setSmsToken(smsToken + "");
                tokenExpireTime = DateTime.getExpireTime();
                user.setActive(false); //the user is active in the start
                user.setCreatedDate(DateTime.getDateTime());
                user.setPassword(bcryptEncoder.encode(user.getPassword()));
                userRepository.save(user);
                return new ResponseEntity<>("User is successfully added", HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This service method is updating the user
     *
     * @param user the user
     * @param httpServletRequest
     * @return response entity
     */
    public ResponseEntity<Object> updateUser(User user, HttpServletRequest httpServletRequest) {
        try {
            Optional<User> userOptional = userRepository.findById(user.getId());
            if(userOptional.isPresent()){
                user.setUpdatedDate(DateTime.getDateTime());
                userRepository.save(user);
                return new ResponseEntity<>("User has been successfully Updated", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User doesn't exist in the database", HttpStatus.OK);
            }

        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>("User is not Updated", HttpStatus.OK);
        }
    }

    /**
     * Delete user from db by using user ID
     *
     * @param id the id
     * @param httpServletRequest
     * @return response entity
     */
    public ResponseEntity<Object> deleteUser(Long id, HttpServletRequest httpServletRequest) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isEmpty()) {
                return new ResponseEntity<>("There is no user against this id", HttpStatus.OK);
            } else {
                user.get().setUpdatedDate(DateTime.getDateTime());
                user.get().setActive(false);
                userRepository.save(user.get());
                return new ResponseEntity<>("User is successfully deleted", HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>("This user doesn't exist in the database", HttpStatus.OK);
        }
    }

    /**
     * This service is authenticating the user from the database
     *
     * @param email    the email
     * @param password the password
     * @return response entity
     */
    public ResponseEntity<Object> loginUser(String email, String password) {
        try {
            Optional<User> user = userRepository.findUserByEmailAndPassword(email, password);
            if (user.isPresent()) {
                return new ResponseEntity<>("You are successfully logged in", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("You are entering wrong credentials", HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Authenticating the user account with sms token and email token
     *
     * @param id         the id
     * @param emailToken the email token
     * @param smsToken   the sms token
     * @param httpServletRequest
     * @return response entity
     */
    public ResponseEntity<Object> AccountVerification(long id, String emailToken, String smsToken, HttpServletRequest httpServletRequest) {
        try {
            Optional<User> user = userRepository.findUserByIdAndEmailTokenAndSmsToken(id,emailToken,smsToken);
            Date verificationTime = DateTime.getDateTime();
            System.out.println(tokenExpireTime);

            if(verificationTime.after(tokenExpireTime)){
                return new ResponseEntity<>("The token is expired", HttpStatus.OK);
            }
            else{
                if (user.isPresent()) {
                    user.get().setActive(true);
                    userRepository.save(user.get());
                    return new ResponseEntity<>("User account has been verified, now you can login", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Your are entering wrong values for tokens", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Resending verification token when user will ask for another token
     *
     * @param email the email
     * @param httpServletRequest
     * @return response entity
     */
    public ResponseEntity<Object> resendVerificationToken(String email, HttpServletRequest httpServletRequest){
        try{
            Optional<User> user = userRepository.findUserByEmail(email);
            if(user.isPresent()){
                Random rnd = new Random(); //Generating a random number
                int emailToken = rnd.nextInt(999999) + 100000; //Generating a random number of 6 digits
                emailNotification.sendMail(user.get().getEmail(), "Your verification code is: " + emailToken);
                user.get().setEmailToken(emailToken+"");

                int smsToken = rnd.nextInt(999999) + 100000;
                smsNotification.Notification(user.get().getPhoneNo(), "Your verification code: " + smsToken);
                user.get().setSmsToken(smsToken + "");
                tokenExpireTime = DateTime.getExpireTime();
                userRepository.save(user.get());
                return new ResponseEntity<>("Tokens are successfully resent to your email and phone number", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User doesn't exist against this email", HttpStatus.OK);
            }
        }catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>("There is no user against this email", HttpStatus.OK);
        }
    }

    /**
     * This method is storing single crime report in the database
     *
     * @param id                the id
     * @param crimeReport       the crime report
     * @param multipartFileList the multipart file list
     * @param httpServletRequest
     * @return response entity
     */
    public ResponseEntity<Object> createCrimeReport(long id, CrimeReport crimeReport, MultipartFile[] multipartFileList, HttpServletRequest httpServletRequest) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isEmpty()) {
                return new ResponseEntity<>("There is no user against this id", HttpStatus.OK);
            } else {
                List<CrimeReport> crimeReports = user.get().getCrimeReports();
                String reportUuid= UuidGenerator.getUuid();
                for (MultipartFile file:multipartFileList
                ) {
                    String reportPictureName = StringUtils.cleanPath(file.getOriginalFilename());
                    String uploadDir = "F:\\Development\\E-Police Project\\Images\\" +reportUuid ;
                    FileUpload.saveFile(uploadDir,reportPictureName, file);
                }
                crimeReport.setUuid(reportUuid);
                crimeReport.setCreatedDate(DateTime.getDateTime());
                crimeReport.setStatus("Pending");
                crimeReport.setActive(true);

                crimeReports.add(crimeReport);

                userRepository.save(user.get());
                return new ResponseEntity<>("Crime Report is successfully added", HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is fetching all the active users from the database
     *
     * @param date the date
     * @param httpServletRequest
     * @return response entity
     */
    public ResponseEntity<Object> findUsersByDate(java.sql.Date date, HttpServletRequest httpServletRequest) {
        try {
            List<User> userList = userRepository.findAllUsersByDate(date);
            if (userList.isEmpty()) {
                return new ResponseEntity<>("There are no users in the database", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(userList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Specific user roles response entity.
     *
     * @param userId the user id
     * @param httpServletRequest
     * @return the response entity
     */
    public ResponseEntity<Object> specificUserRoles(Long userId, HttpServletRequest httpServletRequest){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            UserDto userDto = new UserDto();
            for (Object role:user.get().getRoles()
                 ) {
                userDto.setUsersData(Collections.singleton(role));
            }
            return new ResponseEntity<>(userDto.getUsersData(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No user found against this user id", HttpStatus.OK);
        }
    }

    /**
     * Specific user department response entity.
     *
     * @param userId the user id
     * @param httpServletRequest
     * @return the response entity
     */
    public ResponseEntity<Object> specificUserDepartment(Long userId, HttpServletRequest httpServletRequest){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            UserDto userDto = new UserDto();
            for (Object role:user.get().getCrimeReports()
            ) {
                userDto.setUsersData(Collections.singleton(role));
            }
            return new ResponseEntity<>(userDto.getUsersData(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No user found against this user id", HttpStatus.OK);
        }
    }

    /**
     * This method is getting used by Spring security as well as /login api to authenticate the user
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),getAuthority(user.get()));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    /**
     * Fetching the roles of a user so that can give him the authorities for the apis
     * @param user
     * @return
     */
    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }
}
