package com.example.systemdesign;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//
///*
// The @EnableResourceServer annotation enables our application to behave as a Resource Server by configuring 
// 	an OAuth2AuthenticationProcessingFilter and other equally important components.
//  
// */
//
//@Configuration
//@Order(200)
//@EnableResourceServer
//public class WebSecurityConf extends WebSecurityConfigurerAdapter {
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/access-token").permitAll().anyRequest().authenticated();
//		http.csrf().disable();
//	}
//}

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/*
  The @EnableResourceServer annotation enables our application to behave as a Resource Server by configuring 
	an OAuth2AuthenticationProcessingFilter and other equally important components.

*/
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
//@Order(200)
//@EnableResourceServer
@EnableOAuth2Sso
public class WebSecurityConf extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.antMatcher("/**").authorizeRequests()
		.antMatchers("/", "/index.html","*.js").permitAll()
		.anyRequest().authenticated();
		//http.formLogin();
		//http.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
}
