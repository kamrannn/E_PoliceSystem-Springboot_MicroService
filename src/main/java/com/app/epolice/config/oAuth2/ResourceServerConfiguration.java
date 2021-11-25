package com.app.epolice.config.oAuth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final static String[] adminAccessPoints ={
            "/users/**",
            "/roles/**",
            "/permissions/**",
            "/role-type/**",
            "/room/**",
            "/role-type/**",
            "/police-stations/**",
            "/investigation-team/**",
            "/departments/**",
            "/criminal/**",
            "/crime-type/**",
            "/crime-reports/**"
    };

    private final static String[] publicUserAccessPoints ={
            "/users/update",
            "/users/verification",
            "/users/resend-verification-token",
            "/users/delete/{id}",
            "/users/upload_single_report"
    };

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("api").stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                anonymous().disable()
                .authorizeRequests()
                .antMatchers(adminAccessPoints).hasAuthority("admin")
                .antMatchers(publicUserAccessPoints).hasAuthority("public_user")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
