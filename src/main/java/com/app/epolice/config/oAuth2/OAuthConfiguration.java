package com.app.epolice.config.oAuth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * The type O auth configuration.
 */
@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * The Client id.
     */
    static final String CLIENT_ID = "kamran-client";
    /**
     * The Client secret.
     */
    static final String CLIENT_SECRET = "$2a$12$Y2CVVEELtjcncVpHGPJEk.T47cgJRWUscFN7vpr72hKxVGSqVMThu";
    /**
     * The Grant type password.
     */
    static final String GRANT_TYPE_PASSWORD = "password";
    /**
     * The Authorization code.
     */
    static final String AUTHORIZATION_CODE = "authorization_code";
    /**
     * The Refresh token.
     */
    static final String REFRESH_TOKEN = "refresh_token";
    /**
     * The Implicit.
     */
    static final String IMPLICIT = "implicit";
    /**
     * The Scope read.
     */
    static final String SCOPE_READ = "read";
    /**
     * The Scope write.
     */
    static final String SCOPE_WRITE = "write";
    /**
     * The Trust.
     */
    static final String TRUST = "trust";
    /**
     * The Access token validity seconds.
     */
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
    /**
     * The Refresh token validity seconds.
     */
    static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

    private final AuthenticationManager authenticationManager;

    /**
     * Instantiates a new O auth configuration.
     *
     * @param authenticationManager the authentication manager
     */
    @Autowired
    public OAuthConfiguration(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Access token converter jwt access token converter.
     *
     * @return the jwt access token converter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("as466gf");
        return converter;
    }

    /**
     * Token store token store.
     *
     * @return the token store
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .inMemory()
                .withClient(CLIENT_ID)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
                refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)  {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
    }
}

