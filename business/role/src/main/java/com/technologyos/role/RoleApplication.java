package com.technologyos.role;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.TimeZone;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RoleApplication {

	public static void main(String[] args) {
      TimeZone.setDefault(TimeZone.getTimeZone("GMT-6"));
		SpringApplication.run(RoleApplication.class, args);
	}
}
