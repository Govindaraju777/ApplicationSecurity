# OAuth 2.0 Playground
https://www.oauth.com/playground/

# OAuth Grant Types

https://oauth.net/2/grant-types/

      
      Authorization Code (3 legged )
      Client Credentials (2 legged)
      Device Code
      Refresh Token
      
Legacy:
      Implicit Flow
      Password Grant (Resource Owner Password Flow)




## OAuth Grant Types
https://www.youtube.com/watch?v=1ZX7554l8hY&t=239s


## OAuth 2.0 Client Types
https://oauth.net/2/client-types/#:~:text=Confidential%20clients%20are%20applications%20that,or%20on%20a%20mobile%20device.


    OAuth defines two types of clients: confidential clients and public clients.

    Confidential clients are applications that are able to securely authenticate with the authorization server, for example being able to keep their registered client secret safe.

    Public clients are unable to use registered client secrets, such as applications running in a browser or on a mobile device.


# Front Channel Flow where you authorize via user agent might look as follows
https://developer.okta.com/blog/2017/06/21/what-the-heck-is-oauth

      The front channel flow is used by the client application to obtain an authorization code grant. The back channel is used by the client application to exchange the authorization code grant for an access token (and optionally a refresh token). It assumes the Resource Owner and Client Application are on separate devices



## Spring Boot + OAuth 2 Client Credentials Grant - Hello World Example

https://www.javainuse.com/spring/springboot-oauth2-client-grant

In case of Client credentials grant type the user has no role to play. As previously stated it is machine to machine communication. This is typically used by clients to access resources about themselves rather than to access a user's resources.


    POST /token HTTP/1.1
    Host: authorization-server.com

    grant_type=client_credentials
    &client_id=xxxxxxxxxx
    &client_secret=xxxxxxxxxx
