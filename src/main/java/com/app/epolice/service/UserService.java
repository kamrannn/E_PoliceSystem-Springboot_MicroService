package com.app.epolice.service;

import com.app.epolice.model.entity.user.User;
import com.app.epolice.repository.UserRepository;
import com.app.epolice.util.EmailNotification;
import com.app.epolice.util.SmsNotification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
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
     * This method is fetching all the users from the database
     *
     * @return
     */
    public ResponseEntity<Object> listAllUsers() {
        try {
            List<User> userList = userRepository.findAllByActive(true);
            if (userList.isEmpty()) {
                return new ResponseEntity<>("There are no users in the database", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(userList, HttpStatus.OK);
            }
        } catch (Exception e) {
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
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date = formatter.format(new Date());
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

                user.setActive(false); //the user is active in the start
                user.setCreatedDate(date);
                userRepository.save(user);
                return new ResponseEntity<>("User is successfully added", HttpStatus.OK);
            }
        } catch (Exception e) {
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
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date = formatter.format(new Date());
            user.setUpdatedDate(date);
            userRepository.save(user);
            return new ResponseEntity<>("User has been successfully Updated", HttpStatus.OK);
        } catch (Exception e) {
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
                user.get().setUpdatedDate(date);
                user.get().setActive(false);
                userRepository.save(user.get());
                return new ResponseEntity<>("User is successfully deleted", HttpStatus.OK);
            }
        } catch (Exception e) {
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
            if (user.isPresent()) {
                user.get().setActive(true);
                userRepository.save(user.get());
                return new ResponseEntity<>("User account has been verified, now you can login", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Your are entering wrong values for tokens", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete multiple users from db by using multiple users object
     *
     * @param userList
     * @return
     */
/*    public ResponseEntity<Object> DeleteMultipleUsers(List<User> userList) {
        try {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date = formatter.format(new Date());
            try{
                if(userList.isEmpty()){
                    return new ResponseEntity<>("The entered list is empty", HttpStatus.BAD_REQUEST);
                }else {
                    for (User user : userList
                    ) {
                        Optional<User> existingUser = userRepository.findById(user.getId());
                        if (existingUser.isEmpty()) {
                            return new ResponseEntity<>("There is no user against this id: " + user.getId(), HttpStatus.NOT_FOUND);
                        } else {
                            existingUser.get().setUpdatedDate(date);
                            existingUser.get().setActive(false);
                            userRepository.save(existingUser.get());
                            return new ResponseEntity<>("Users are successfully deleted", HttpStatus.OK);
                        }
                    }
                }
                return new ResponseEntity<>("Successfully added",HttpStatus.OK);
            }catch (Exception exception){
                return null;
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
