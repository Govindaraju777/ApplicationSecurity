///**
// * 
// */
//package com.example.springcloud.oauthserver;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author govindaraju.v
// *
// */
//@Configuration
//@Order(1)
//public class AppWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.requestMatchers().antMatchers("/login","/oauth/token").and().authorizeRequests().anyRequest().authenticated().and().formLogin().permitAll();
//		http.httpBasic().disable();
//	}
//	@Override
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//	    return super.authenticationManagerBean();
//	}
//}
