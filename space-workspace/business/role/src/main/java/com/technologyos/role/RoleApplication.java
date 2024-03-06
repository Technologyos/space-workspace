package com.technologyos.role;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class RoleApplication {

	public static void main(String[] args) {
      TimeZone.setDefault(TimeZone.getTimeZone("GMT-6"));
		SpringApplication.run(RoleApplication.class, args);
	}
}
