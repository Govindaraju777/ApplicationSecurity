package com.example.systemdesign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class TinyUrlOauthClientWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinyUrlOauthClientWebApplication.class, args);
	}

}
