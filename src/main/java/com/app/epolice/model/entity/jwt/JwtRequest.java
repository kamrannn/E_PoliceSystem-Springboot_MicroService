package com.app.epolice.model.entity.jwt;

import java.io.Serializable;

/**
 * The type Jwt request.
 */
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;

    /**
     * Instantiates a new Jwt request.
     */
//need default constructor for JSON Parsing
    public JwtRequest() {

    }

    /**
     * Instantiates a new Jwt request.
     *
     * @param username the username
     * @param password the password
     */
    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}