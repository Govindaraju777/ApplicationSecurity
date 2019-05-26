# SpringSecurity


Authentication with a Database-backed UserDetailsService
	https://www.baeldung.com/spring-security-authentication-with-a-database
	
	https://www.codementor.io/gtommee97/rest-authentication-with-spring-security-and-mongodb-j8wgh8kg7
	
	https://medium.com/@gtommee97/rest-authentication-with-spring-security-and-mongodb-28c06da25fb1
	
	https://ingini.org/2015/03/26/authentication-authorization-schema-design-with-mongodb/
	

Inserting user with Bcrypt Password in MongodB:
	- Generate BCrypt passowrd: https://www.browserling.com/tools/bcrypt
	- Plain text password : secret1   Bcyrppassword  : $2a$10$J0i24V1W2UxUYyogYsFwOueHq2BAzBUIWDwPHweaoxWhOJH9AtPe.
	Mongo insert Query : 
	db.users.insert({"username" : "user1","password" : "$2a$10$J0i24V1W2UxUYyogYsFwOueHq2BAzBUIWDwPHweaoxWhOJH9AtPe."});
	db.users.insert({"username" : "admin1","password" : "$2a$10$J0i24V1W2UxUYyogYsFwOueHq2BAzBUIWDwPHweaoxWhOJH9AtPe."});

