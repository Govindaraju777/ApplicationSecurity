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
	
	
	// Authentication : User --> Roles
		/* Prior to Spring Security 5.0 the default PasswordEncoder was NoOpPasswordEncoder 
		  which required plain text passwords. In Spring Security 5, 
		  the default is DelegatingPasswordEncoder, which required Password Storage Format.
		  
		  https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-storage-format
		 */ 
		
			/*
			//password encoder solution -2
		    @Bean
	    	public UserDetailsService userDetailsService() {
		        User.UserBuilder users = User.withDefaultPasswordEncoder();
		        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		        manager.createUser(users.username("user").password("password").roles("USER").build());
		        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
		        return manager;
	    	}
		 */
		
		/*
		 //inmemory DB user
		 protected void configure(AuthenticationManagerBuilder authBuilder)
				throws Exception {
			auth.inMemoryAuthentication().withUser("user1").password("{noop}secret1")
					.roles("USER").and().withUser("admin1").password("{noop}secret1")
					.roles("USER", "ADMIN");
		}*/
		// Authorization : Role -> Access
			/*protected void configure(HttpSecurity http) throws Exception {
				http.httpBasic().and().authorizeRequests().antMatchers("/students/**","/")
						.hasRole("USER").antMatchers("/**").hasRole("ADMIN").and()
						.csrf().disable().headers().frameOptions().disable();
				//http.httpBasic().and().authorizeRequests().antMatchers("/*").permitAll();
		}*/
}
