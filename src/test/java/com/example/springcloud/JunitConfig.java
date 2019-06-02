package com.example.springcloud;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.web.client.RestTemplate;

@Configuration
public class JunitConfig {

	@Value("${accessTokenUri}")
	private String accessTokenUri;

	@Value("${userAuthorizationUri}")
	private String userAuthorizationUri;

	@Value("${clientID}")
	private String clientID;

	@Value("${clientSecret}")
	private String clientSecret;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/*@Bean
	public OAuth2RestTemplate redditRestTemplate(OAuth2ClientContext clientContext) {
		OAuth2RestTemplate template = new OAuth2RestTemplate(reddit(), clientContext);
		AccessTokenProvider accessTokenProvider = new AccessTokenProviderChain(Arrays.<AccessTokenProvider> asList(
				//new MyAuthorizationCodeAccessTokenProvider(),
				new ImplicitAccessTokenProvider(), new ResourceOwnerPasswordAccessTokenProvider(), new ClientCredentialsAccessTokenProvider()));
		template.setAccessTokenProvider(accessTokenProvider);
		return template;
	}*/

	/*@Bean
	public OAuth2ProtectedResourceDetails reddit() {
		AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
		details.setId("reddit");
		details.setClientId(clientID);
		details.setClientSecret(clientSecret);
		details.setAccessTokenUri(accessTokenUri);
		details.setUserAuthorizationUri(userAuthorizationUri);
		details.setTokenName("oauth_token");
		details.setScope(Arrays.asList("identity"));
		details.setPreEstablishedRedirectUri("http://localhost/login");
		details.setUseCurrentUri(false);
		return details;
	}*/
}
