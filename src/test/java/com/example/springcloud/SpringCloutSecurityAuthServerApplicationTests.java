package com.example.springcloud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


//https://www.baeldung.com/spring-security-oauth2-authentication-with-reddit


//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringCloutSecurityAuthServerApplicationTests {

	@Autowired
	RestTemplate restTemplate;
	// @Autowired
	// OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;
	//
	// @Autowired
	// ClientDetails client;

	@Test
	public void contextLoads() {

	}

	@Test
	public void testTokenGenerator() {
		/*restTemplate = new RestTemplate();
		LocalClient client = new LocalClient("grant_type","makeymytrip","secretkey","govind","password");

		String base64encodedString = Base64.getEncoder().encodeToString("makeymytrip:secretkey".getBytes());
		System.out.println("base64encodedString : " + base64encodedString);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic " + base64encodedString);
		headers.set("Content-type", "application/x-www-form-urlencoded");
		headers.set("cache-control", "no-cache");
		headers.set("Postman-Token", "0babb329-252e-4ff8-9668-c4d81691d5c8");

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));         
		messageConverters.add(converter);  

		HttpEntity<Object> httpEntity = new HttpEntity<Object>(client, headers);
		ResponseEntity<OAuth2AccessToken> response = restTemplate.postForEntity("http://localhost:9000/service/oauth/token", httpEntity, OAuth2AccessToken.class);

		System.out.println("");*/
		
		/*
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "grant_type=password&client_id=makeymytrip&client_secret=secretkey&username=govind&password=password&undefined=");
		Request request = new Request.Builder()
		  .url("http://localhost:9000/service/oauth/token")
		  .post(body)
		  .addHeader("Content-Type", "application/x-www-form-urlencoded")
		  .addHeader("Authorization", "Basic bWFrZXlteXRyaXA6c2VjcmV0a2V5")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("Postman-Token", "0babb329-252e-4ff8-9668-c4d81691d5c8")
		  .build();

		Response response = client.newCall(request).execute();
		*/
		
		try {
			HttpResponse<String> response = Unirest.post("http://localhost:9000/service/oauth/token")
					  .header("Content-Type", "application/x-www-form-urlencoded")
					  .header("Authorization", "Basic bWFrZXlteXRyaXA6c2VjcmV0a2V5")
					  .header("cache-control", "no-cache")
					  .header("Postman-Token", "87cd6f14-fe26-4367-8663-fefb1349bcf5")
					  .body("grant_type=password&client_id=makeymytrip&client_secret=secretkey&username=govind&password=password")
					  .asString();
					System.out.println("response body " + response.getBody());
			
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		
	}

}

class LocalClient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4757547713398291252L;
	LocalClient() {
	}

	LocalClient(String grant_type, String client_id, String client_secret, String username, String password) {
		this.grant_type = grant_type;
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.username = username;
		this.password = password;
	}

	String grant_type;
	String client_id;
	String client_secret;
	String username;
	String password;
	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}