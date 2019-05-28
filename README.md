# SpringSecurity

SpringSecurity OAuth:

OAuth2

	OAuth2 is an authorization framework that enables the application Web Security to access the resources from the client. To build an OAuth2 application, we need to focus on the Grant Type (Authorization code), Client ID and Client secret.

Sample Web Application using Spring MVC and Oauth login using (Git, Goolge accout etc..)


Maven Dependencies : 
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-oauth2</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-security</artifactId>
		</dependency>
		
		
src/main/resources/application.properties		
		
		#git SSO Login 
		security.oauth2.client.client-id= bd1c0a783ccdd1c9b9e4
		security.oauth2.client.client-secret= 1a9030fbca47a5b2c28e92f19050bb77824b5ad1
		security.oauth2.client.access-token-uri= https://github.com/login/oauth/access_token
		security.oauth2.client.userAuthorizationUri= https://github.com/login/oauth/authorize
		security.oauth2.resource.user-info-uri=https://api.github.com/user
		security.oauth2.client.client-authentication-scheme=form

		
App Main :

	package com.example.systemdesign;

	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

	@SpringBootApplication
	public class TinyUrlOauthClientWebApplication {

		public static void main(String[] args) {
			SpringApplication.run(TinyUrlOauthClientWebApplication.class, args);
		}

	}


SSO Client :


	package com.example.systemdesign;

	import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
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




Home Page Controller : 

	package com.example.systemdesign.tinyurl.controllers;

	import org.springframework.context.annotation.Configuration;
	import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
	import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

	@Configuration
	public class HomePageController implements WebMvcConfigurer {
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/").setViewName("tinyUrl");
		}
	}


Sample service class : 

	package com.example.systemdesign.tinyurl.controllers;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
	import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
	import org.springframework.security.oauth2.client.OAuth2ClientContext;
	import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.ResponseBody;
	import org.springframework.web.servlet.ModelAndView;
	import org.springframework.web.servlet.mvc.support.RedirectAttributes;
	import org.springframework.web.servlet.view.RedirectView;

	import com.example.systemdesign.tinyurl.service.TinyKeyGenerationService;

	/**
	 * @author govindaraju.v
	 *
	 */
	@Controller
	public class TinyKeyGenerationController {

		@Autowired
		TinyKeyGenerationService tinyKeyGenerationService;

		@Autowired
		OAuth2ClientContext clientContext;

		// @RequestMapping(value = "/generateShortURL", method = RequestMethod.GET)
		@PostMapping(value = "/generateShortURL")
		public ModelAndView generateUrl(@RequestParam("originalURL") String url, ModelAndView model) {
			String tinyUrl = tinyKeyGenerationService.generateUrl(url);
			model.setViewName("tinyUrl");
			model.getModelMap().addAttribute("tinyUrl", tinyUrl);
			model.getModelMap().addAttribute("originalUrl", url);
			return model;
		}

		@GetMapping(value = "/{tinyUrl}")
		public RedirectView redirectTinyUrl(RedirectAttributes attributes, @PathVariable("tinyUrl") String tinyUrl) {
			String revRedirectUrl = tinyKeyGenerationService.reverseURL(tinyUrl);
			return new RedirectView(revRedirectUrl);
		}

		@GetMapping(value = "/access-token")
		@ResponseBody
		public String appTokenGenerator() {
			String token = clientContext.getAccessToken().getValue();
			System.out.println("access token : " + token);
			return token;
		}
		@GetMapping(value = "/user")
		@ResponseBody
		public Principal user(Principal principal) {
			return principal;
		}

	}


	package com.example.systemdesign.tinyurl.service;

	/**
	 * @author govindaraju.v
	 *
	 */
	public interface TinyKeyGenerationService {
		public String generateUrl(String url);

		public String reverseURL(String encodedTinyURL);
	}


	
	package com.example.systemdesign.tinyurl.service;

	import java.util.HashMap;
	import java.util.Map;
	import java.util.concurrent.atomic.AtomicInteger;

	import org.springframework.stereotype.Service;

	@Service
	public class TinyKeyGenerationServiceImpl implements TinyKeyGenerationService {

		public TinyKeyGenerationServiceImpl() {
			System.out.println("--------TinyKeyGenerationServiceImpl--------");
		}

		// base62
		private static final String CHAR_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		private final int base = CHAR_STR.length();
		private Map<String, String> urlStrore = new HashMap<>();
		AtomicInteger counter = new AtomicInteger(10);

		public String generateUrl(String url) {
			if (url == null) {
				throw new NullPointerException();
			}
			final long nextNumber = getNextNumber();
			String tinyUrl = convertAndGetBase62Code(nextNumber);
			urlStrore.put(tinyUrl, url);
			return tinyUrl;
		}

		private String convertAndGetBase62Code(long number) {
			StringBuilder sb = new StringBuilder("");
			while (number > 0) {
				int reminder = (int) (number % base);
				sb.append(CHAR_STR.charAt(reminder));
				number = number / base;
			}
			return sb.toString();
		}

		private long getNextNumber() {
			int counterValue = counter.incrementAndGet();
			return Long.valueOf(counterValue + System.currentTimeMillis());
		}

		public String reverseURL(String encodedTinyURL) {
			// converttoBase10... long tinyURLId =
			return urlStrore.get(encodedTinyURL);
		}
	}



src/main/resources/templates/tinyUrl.html

		<!DOCTYPE html>
		<html xmlns:th="http://www.thymeleaf.org">
		<head>
		<meta charset="UTF-8">
		<title>tinyUrl</title>
		</head>
		<body>

			<div>
				<span th:if="${tinyUrl!=null}">
					<p>TinyURL was created!</p> <!-- <p th:text="'Short URL is ' + ${tinyUrl} +' ' + ' ,for originalUrl ' + ${originalUrl}"></p> -->
					<a th:href="${tinyUrl}">Open shorten url</a>
				</span>
			</div>
			<p>Enter a long URL to make tiny:</p>
			<form action="/generateShortURL" method="post">
				<span> <input type="text" name="originalURL" /> <input
					type="hidden" th:name="${_csrf.parameterName}"
					th:value="${_csrf.token}" /><input type="submit"
					title="Make ShortURL!">
			</form>
		</body>
		</html>



Test:

http://localhost:8080
![alt text](https://github.com/Govindaraju777/SpringSecurity/blob/Branch3_SpringOAuthClient_WEBAppExample/OauthLogin1.png)

http://localhost:8080
![alt text](https://github.com/Govindaraju777/SpringSecurity/blob/Branch3_SpringOAuthClient_WEBAppExample/OauthLogin2.png)

http://localhost:8080
![alt text](https://github.com/Govindaraju777/SpringSecurity/blob/Branch3_SpringOAuthClient_WEBAppExample/OauthLogin3.png)
