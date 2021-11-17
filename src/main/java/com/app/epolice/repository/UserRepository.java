package com.app.epolice.repository;

import com.app.epolice.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find all by active list.
     *
     * @param active the active
     * @return the list
     */
    List<User> findAllByActive(boolean active);

    /**
     * Find user by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);

    /**
     * Find user by username user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);

    /**
     * Find user by email and password optional.
     *
     * @param email    the email
     * @param password the password
     * @return the optional
     */
    Optional<User> findUserByEmailAndPassword(String email, String password);

    /**
     * Find user by id and email token and sms token optional.
     *
     * @param id         the id
     * @param emailToken the email token
     * @param smsToken   the sms token
     * @return the optional
     */
    Optional<User> findUserByIdAndEmailTokenAndSmsToken(long id ,String emailToken, String smsToken);

    /**
     * Find all by active true order by created date desc list.
     *
     * @return the list
     */
    List<User> findAllByActiveTrueOrderByCreatedDateDesc();

    /**
     * Find all users by date list.
     *
     * @param date the date
     * @return the list
     */
    @Query(value = "SELECT * FROM t_user where created_date like CONCAT(:date,'%')", nativeQuery = true)
    List<User> findAllUsersByDate(Date date);
}