	OAuth2 Terminology : http://blog.marcosbarbero.com/centralized-authorization-jwt-spring-boot2/
		Resource Owner
		The user who authorizes an application to access his account. The access is limited to the scope.
		
		Resource Server:
		A server that handles authenticated requests after the client has obtained an access token.
		
		Client
		An application that access protected resources on behalf of the resource owner.
		
		Authorization Server
		A server which issues access tokens after successfully authenticating a client and resource owner, and authorizing the request.
		
		Access Token
		A unique token used to access protected resources
		
		Scope
		A Permission
		
		JWT
		JSON Web Token is a method for representing claims securely between two parties as defined in RFC 7519
		
		Grant type
		A grant is a method of acquiring an access token.
		Read more about grant types here
	
	
	
#Spring Boot and OAuth2: Getting the Authorization Code
	
	https://dzone.com/articles/spring-boot-oauth2-getting-the-authorization-code

	 	<dependency>
	            <groupId>org.springframework.security.oauth</groupId>
	            <artifactId>spring-security-oauth2</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-starter-web</artifactId>
	        </dependency>
	        <dependency>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-starter-security</artifactId>
	        </dependency>   
	
	 
	 Centralized Authorization with OAuth2 & JWT using Spring Boot 2
	 https://www.javacodegeeks.com/2019/03/centralized_-authorization_-oauth2_jwt.html
	        
	        
#Building our own OAuth server :

	application.yml

	server:
	  port: 9000
	  servlet.context-path: /service
	  tomcat:
	    max-threads: 100
	security:
	  oauth2:
	    client:
	      clientId: makeymytrip
	      client-secret: secretkey
	      grant-type: password,client_credentials,authorization_code,refresh_token
	      scope: read,write
	spring:
	  security:
	    user:
	      name: govind
	      password: password
	      roles: USER
	logging:
	  level:
	    root: INFO
	    org-springframework.*: DEBUG
	 


	       
	Curl command to generate token 
	# encode userName and password to basicAuthentication & Trim any trailing "=" from the end of the encoded string.
	#EXIMR-MB-316:~ govindaraju.v$ echo -n 'makeymytrip:secretkey' | openssl base64
	#  bWFrZXlteXRyaXA6c2VjcmV0a2V5



	
	curl -X POST \
	http://localhost:9000/service/oauth/token \
	-H 'Authorization: Basic bWFrZXlteXRyaXA6c2VjcmV0a2V5' \
	-H 'Content-Type: application/x-www-form-urlencoded' \
	-H 'Postman-Token: 07556e9e-f448-4d08-bb28-f227e0298761' \
	-H 'cache-control: no-cache' \
	-d 'grant_type=password&client_id=makeymytrip&client_secret=secretkey&username=govind&password=password&undefined='


![alt text](https://github.com/Govindaraju777/SpringSecurity/blob/Branch4_SpringCloutSecurity_OAuthServer/oauthServerCurl.png)

![alt text](https://github.com/Govindaraju777/SpringSecurity/blob/Branch4_SpringCloutSecurity_OAuthServer/OauthServerToken_Postman_1.png)
![alt text](https://github.com/Govindaraju777/SpringSecurity/blob/Branch4_SpringCloutSecurity_OAuthServer/OauthServerToken_Postman_2.png)
![alt text](https://github.com/Govindaraju777/SpringSecurity/blob/Branch4_SpringCloutSecurity_OAuthServer/OauthServerToken_Postman_3.png)
	        
        
        