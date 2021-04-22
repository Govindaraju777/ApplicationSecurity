# OAuth 2.0 Playground
https://www.oauth.com/playground/

# OAuth Grant Types

https://oauth.net/2/grant-types/

      Authorization Code
      Client Credentials
      Device Code
      Refresh Token



## Spring Boot + OAuth 2 Client Credentials Grant - Hello World Example

https://www.javainuse.com/spring/springboot-oauth2-client-grant

In case of Client credentials grant type the user has no role to play. As previously stated it is machine to machine communication. This is typically used by clients to access resources about themselves rather than to access a user's resources.


    POST /token HTTP/1.1
    Host: authorization-server.com

    grant_type=client_credentials
    &client_id=xxxxxxxxxx
    &client_secret=xxxxxxxxxx
