package com.app.epolice.config.oAuth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * The type Resource server configuration.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final static String[] adminAccessPoints = {
            "/users/**",
            "/roles/**",
            "/permissions/**",
            "/room/**",
            "/room-type/**",
            "/police-stations/**",
            "/investigation-team/**",
            "/departments/**",
            "/criminal/**",
            "/crime-type/**",
            "/crime-reports/**"
    };

    private final static String[] publicUserAccessPoints = {
            "/users/update",
            "/users/resend-verification-token",
            "/users/delete/{id}",
            "/users/upload_single_report",
            "/police-stations/list"
    };

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("api").stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("swagger-ui.html/", "/swagger-ui.html/", "/api-docs/**", "/v2/api-docs/**", "/permissions/add", "/roles/add", "/police-stations/add", "/departments/add", "/users/signup", "/users/verification","/users/oauth/logout").permitAll()
                .antMatchers(adminAccessPoints).hasAuthority("admin")
                .antMatchers(publicUserAccessPoints).hasAuthority("public_user")
                .antMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

/*        http.
                anonymous().disable()
                .authorizeRequests()
                .antMatchers("/permissions/add","/roles/add","/police-stations/add","/departments/add").permitAll()
                .antMatchers(adminAccessPoints).hasAnyAuthority("admin")
                .antMatchers(publicUserAccessPoints).hasAnyAuthority("public_user")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());*/
    }
}
