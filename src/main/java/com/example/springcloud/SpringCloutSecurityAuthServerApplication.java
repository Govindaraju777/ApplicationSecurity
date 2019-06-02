package com.example.springcloud;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class SpringCloutSecurityAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloutSecurityAuthServerApplication.class, args);
	}

	//@RequestMapping(value = "/user", method = RequestMethod.GET)
	@GetMapping(value = "/user")
	public Principal getUser(Principal user) {
		return user;
	}
}
