package com.l1nd53y.mod2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
public class Restfulapimod2Application {

	public static void main(String[] args) {
		SpringApplication.run(Restfulapimod2Application.class, args);

	}
}

//(exclude = SecurityAutoConfiguration.class)
