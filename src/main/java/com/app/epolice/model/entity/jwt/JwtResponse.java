package com.app.epolice.model.entity.jwt;

import java.io.Serializable;

/**
 * The type Jwt response.
 */
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;

    /**
     * Instantiates a new Jwt response.
     *
     * @param jwtToken the jwt token
     */
    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return this.jwtToken;
    }
}