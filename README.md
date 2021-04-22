# OAuth 2.0 Playground
https://www.oauth.com/playground/


# OAuth Central Components

https://developer.okta.com/blog/2017/06/21/what-the-heck-is-oauth


      OAuth is built on the following central components:

      Scopes and Consent
      Actors
      Clients
      Tokens
      Authorization Server
      Flows
      
      
#### Endpoints
      OAuth 2.0 uses two endpoints: the /authorize endpoint and the /oauth/token endpoint.
https://auth0.com/docs/protocols/protocol-oauth2

      


# OAuth Grant Types

https://oauth.net/2/grant-types/

      
      1. Authorization Code (3 legged )
      2. Client Credentials (2 legged)
      3. Device Code
      4. Refresh Token
      
# Legacy Grant Types :
            1. Implicit Flow
            2. Password Grant (Resource Owner Password Flow)


 #####     1. Implicit Flow
      
            The Implicit flow was a simplified OAuth flow previously recommended for native apps and JavaScript apps where the access token was returned immediately without an extra authorization code exchange step.

      It is not recommended to use the implicit flow (and some servers prohibit this flow entirely) due to the inherent risks of returning access tokens in an HTTP redirect without any confirmation that it has been received by the client.

      Public clients such as native apps and JavaScript apps should now use the authorization code flow with the PKCE extension instead.


###### The Implicit Grant
            Like the Authorization Code Grant Type, the Implicit Grant starts out by building a link and directing the user’s browser to that URL. At a high level, the flow has the following steps:

            The application opens a browser to send the user to the OAuth server
            The user sees the authorization prompt and approves the app’s request
            The user is redirected back to the application with an access token in the URL fragment



####     2.  Password Grant (Resource Owner Password Flow)

                  https://auth0.com/docs/flows/resource-owner-password-flow
                  
                  The Password grant type is a way to exchange a user's credentials for an access token. Because the client application has to collect the user's password and send it to the authorization server, it is not recommended that this grant be used at all anymore.

                  This flow provides no mechanism for things like multifactor authentication or delegated accounts, so is quite limiting in practice.




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
