package com.app.epolice.service;

import com.app.epolice.controller.UserController;
import com.app.epolice.model.entity.crime.CrimeReport;
import com.app.epolice.model.entity.user.User;
import com.app.epolice.repository.UserRepository;
import com.app.epolice.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    private Date tokenExpireTime = null;

    /**
     * Initializing the Repositories
     */
    final UserRepository userRepository;
    final EmailNotification emailNotification;
    final SmsNotification smsNotification;

    /**
     * Parameterized constructor
     *
     * @param userRepository
     */
    public UserService(UserRepository userRepository,EmailNotification emailNotification,SmsNotification smsNotification) {
        this.userRepository = userRepository;
        this.emailNotification = emailNotification;
        this.smsNotification = smsNotification;
    }

    /**
     * This method is fetching all the active users from the database
     *
     * @return
     */
    public ResponseEntity<Object> listAllActiveUsers() {
        try {
            List<User> userList = userRepository.findAllByActiveTrueOrderByCreatedDateDesc();
            if (userList.isEmpty()) {
                return new ResponseEntity<>("There are no users in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(userList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is fetching all the InActive users from the database
     *
     * @return
     */
    public ResponseEntity<Object> listOfInActiveUsers() {
        try {
            List<User> userList = userRepository.findAllByActive(false);
            if (userList.isEmpty()) {
                return new ResponseEntity<>("There are no users in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(userList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is adding the user
     *
     * @param user
     * @return
     */
    public ResponseEntity<Object> addUser(User user) {
        try {
            Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                if (existingUser.get().isActive()) {
                    return new ResponseEntity<>("User already present", HttpStatus.BAD_REQUEST);
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
                userRepository.save(user);
                return new ResponseEntity<>("User is successfully added", HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This serivce method is updating the user
     *
     * @param user
     * @return
     */
    public ResponseEntity<Object> updateUser(User user) {
        try {
/*            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date = formatter.format(new Date());*/
            user.setUpdatedDate(DateTime.getDateTime());
            userRepository.save(user);
            return new ResponseEntity<>("User has been successfully Updated", HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>("User is not Updated", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete user from db by using user ID
     *
     * @param id
     * @return
     */
    public ResponseEntity<Object> deleteUser(Long id) {
        try {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date = formatter.format(new Date());
            Optional<User> user = userRepository.findById(id);
            if (user.isEmpty()) {
                return new ResponseEntity<>("There is no user against this id", HttpStatus.NOT_FOUND);
            } else {
                user.get().setUpdatedDate(DateTime.getDateTime());
                user.get().setActive(false);
                userRepository.save(user.get());
                return new ResponseEntity<>("User is successfully deleted", HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>("This user doesn't exist in the database", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This service is authenticating the user from the database
     *
     * @param email
     * @param password
     * @return
     */
    public ResponseEntity<Object> loginUser(String email, String password) {
        try {
            Optional<User> user = userRepository.findUserByEmailAndPassword(email, password);
            if (user.isPresent()) {
                return new ResponseEntity<>("You are successfully logged in", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("You are entering wrong credentials", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Authenticating the user account with sms token and email token
     * @param id
     * @param smsToken
     * @param emailToken
     * @return
     */
    public ResponseEntity<Object> AccountVerification(long id, String emailToken, String smsToken) {
        try {
            Optional<User> user = userRepository.findUserByIdAndEmailTokenAndSmsToken(id,emailToken,smsToken);
            Date verificationTime = DateTime.getDateTime();
            System.out.println(tokenExpireTime);

            if(verificationTime.after(tokenExpireTime)){
                return new ResponseEntity<>("The token is expired", HttpStatus.BAD_REQUEST);
            }
            else{
                if (user.isPresent()) {
                    user.get().setActive(true);
                    userRepository.save(user.get());
                    return new ResponseEntity<>("User account has been verified, now you can login", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Your are entering wrong values for tokens", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete multiple users from db by using multiple users object
     *
     * @param userList
     * @return
     */
    public ResponseEntity<Object> DeleteMultipleUsers(List<User> userList) {
        try {
            if (userList.isEmpty()) {
                return new ResponseEntity<>("The entered list is empty", HttpStatus.BAD_REQUEST);
            } else {
                for (User user : userList
                ) {
                    Optional<User> existingUser = userRepository.findById(user.getId());
                    if (existingUser.isEmpty()) {
                        return new ResponseEntity<>("There is no user against this id: " + user.getId(), HttpStatus.NOT_FOUND);
                    } else {
                        existingUser.get().setUpdatedDate(DateTime.getDateTime());
                        existingUser.get().setActive(false);
                        userRepository.save(existingUser.get());
                        return new ResponseEntity<>("Users are successfully deleted", HttpStatus.OK);
                    }
                }
            }
            return new ResponseEntity<>("Successfully added", HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Exception"+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Resending verification token when user will ask for another token
     * @param email
     * @return
     */
    public ResponseEntity<Object> resendVerificationToken(String email){
        try{
            Optional<User> user = userRepository.findUserByEmail(email);
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
        }catch (Exception e) {
            LOG.info("Exception: "+ e.getMessage());
            return new ResponseEntity<>("There is no user against this email", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is storing single crime report in the database
     * @param crimeReport
     * @return
     */
    public ResponseEntity<Object> createCrimeReport(long id,CrimeReport crimeReport, MultipartFile[] multipartFileList) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isEmpty()) {
                return new ResponseEntity<>("There is no user against this id", HttpStatus.BAD_REQUEST);
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
}
