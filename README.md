# SpringSecurity


		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>
		

Authentication with a Database-backed UserDetailsService
	https://www.baeldung.com/spring-security-authentication-with-a-database
	
	https://www.codementor.io/gtommee97/rest-authentication-with-spring-security-and-mongodb-j8wgh8kg7
	
	https://medium.com/@gtommee97/rest-authentication-with-spring-security-and-mongodb-28c06da25fb1
	
	https://ingini.org/2015/03/26/authentication-authorization-schema-design-with-mongodb/
	
	https://www.djamware.com/post/5b2f000880aca77b083240b2/spring-boot-security-and-data-mongodb-authentication-example
	

Inserting user with Bcrypt Password in MongodB:
	- Generate BCrypt passowrd: https://www.browserling.com/tools/bcrypt
	- Plain text password : secret1   Bcyrppassword  : $2a$10$J0i24V1W2UxUYyogYsFwOueHq2BAzBUIWDwPHweaoxWhOJH9AtPe.
	Mongo insert Query : 
	db.users.insert({"username" : "user1","password" : "$2a$10$J0i24V1W2UxUYyogYsFwOueHq2BAzBUIWDwPHweaoxWhOJH9AtPe."});
	db.users.insert({"username" : "admin1","password" : "$2a$10$J0i24V1W2UxUYyogYsFwOueHq2BAzBUIWDwPHweaoxWhOJH9AtPe."});



/**
 * 
 */
package com.example.springboot.repositories.security;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.Users;

/**
 * @author govindaraju.v
 *
 */
@Repository
public interface UsersRepository extends MongoRepository<Users, String> {
	Users findByUsername(String username);
}


/**
 * 
 */
package com.example.springboot.service.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springboot.model.Users;
import com.example.springboot.repositories.security.UsersRepository;

/**
 * @author govindaraju.v
 *
 */
@Service
public class MongoUserDetailsService implements UserDetailsService {

	@Autowired
	UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		return new User(user.getUsername(), user.getPassword(), getUserAuthority(user));
	}

	private List<GrantedAuthority> getUserAuthority(Users user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
		return authorities;
	}

}






package com.example.springboot.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springboot.service.security.MongoUserDetailsService;

@Configuration
//@EnableConfigurationProperties
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	MongoUserDetailsService userDetailsService;
	
	//configure Jdbc Database user 
	@Bean
	public PasswordEncoder passwordEncoder() {
	 return new BCryptPasswordEncoder();
	}
	@Override
	public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.userDetailsService(userDetailsService);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  //.authorizeRequests().anyRequest().authenticated()
	  http
	    .authorizeRequests()
	    .antMatchers("/").permitAll()
	    .antMatchers("/students/**","/usernameAdminUrl").hasAuthority("ADMIN")
	    .anyRequest().authenticated()
	    .and()
	    .csrf().disable()
	    .httpBasic()
	    .and().sessionManagement().disable();
	}	
}




package com.example.springboot.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggedInUserDetailsController {

	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public Principal currentUser(Principal principal) {
		return principal;
	}

	@RequestMapping(value = "/myName", method = RequestMethod.GET)
	public String currentUserName(Principal principal) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			System.out.println("currentUserName using static call to the SecurityContextHolder:: " + currentUserName);
		}
		System.out.println("currentUserName using principal Object :: " + principal.getName());
		return principal.getName();
	}

	// Alternatively, the authentication token can also be used:
	@RequestMapping(value = "/username1", method = RequestMethod.GET)
	public String currentUserName(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println("User has authorities: " + userDetails.getAuthorities());
		return authentication.getName();
	}

	@RequestMapping(value = "/usernameAdminUrl", method = RequestMethod.GET)
	@ResponseBody
	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		return principal.getName();
	}

}


http://localhost:8080/usernameAdminUrl


