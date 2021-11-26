package com.app.epolice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * The type E police application.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Configuration
@EnableWebSecurity
public class EPoliceApplication {
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
        SpringApplication.run(EPoliceApplication.class, args);
    }
}
