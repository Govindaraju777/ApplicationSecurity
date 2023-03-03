# SpringSecurity

Branch 1 : Branch1_SpringBasicAuth
	Spring mvc application with Basic spring security enabled with InMemoryDB users authentication
	https://github.com/Govindaraju777/SpringSecurity/tree/Branch1_SpringBasicAuth

Branch 2 : Branch2_SpringBasicAuth_Database_backedUserDetails	
	Spring mvc application with Basic spring security enabled with JDBC(mongoDb) users authentication
	https://github.com/Govindaraju777/SpringSecurity/tree/Branch2_SpringBasicAuth_Database_backedUserDetails

Branch 3 : Branch3_SpringOAuthClient_WEBAppExample	
	Spring mvc application with Oauth Client Enabled - allows login using Oauth accounts such as Git, Google etc.
	https://github.com/Govindaraju777/SpringSecurity/tree/Branch3_SpringOAuthClient_WEBAppExample






# OAuth2 docs
https://github.com/Govindaraju777/SpringSecurity/tree/Oauth2_OIDC-docs


https://www.baeldung.com/spring-boot-security-autoconfiguration
WSO2 API Manager - https://youtu.be/iRF97aCQxyc?t=849


## Deep dive with SSL certificates
The process of establishing a secured channel between a server and a client involves in creating an encrypted connection between the server and the client.
https://medium.com/geekculture/story-of-ssl-certificates-161f29df8b65
## Digital Certificates: Chain of Trust
https://www.youtube.com/watch?v=heacxYUnFHA&t=699s


# Oauth vs OIDC
	
	OAuth (Open Authorization) and OIDC (OpenID Connect) are both protocols used for authentication and authorization, but they have different purposes and functionalities.

	OAuth is primarily used for authorization, allowing a user to grant permissions to a third-party application to access their resources (e.g., photos, contacts, etc.) 
	on a server without giving the third-party application access to the user's login credentials. 
	OAuth is commonly used in social media applications like Facebook, Twitter, and Google, where users can authorize third-party applications to access their account information without sharing their username and password.


###  OIDC
	On the other hand, OIDC is a protocol built on top of OAuth 2.0 and is designed for authentication. OIDC provides an ID token, which is a JSON web token containing information about the user that can be used to authenticate the user to a service. 
	OIDC also provides additional security features such as session management, token revocation, and user consent.

	In summary, OAuth is a protocol for authorization, while OIDC is a protocol for authentication. They both work together, and OIDC relies on OAuth for authorization to gain access to resources.



