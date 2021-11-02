package com.app.epolice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class EPoliceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EPoliceApplication.class, args);
	}
}
