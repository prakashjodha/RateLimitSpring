# RateLimitSpring
Spring boot application with H2 DB to demonstrate the leaking bucket based implementation of Rate Limit.

Techniques Used: Leaky Bucket
	A leaky bucket is similar to a token bucket, but the rate is limited by the amount that can drip or leak
	out of the bucket. This technique recognizes that the system has some degree of finite capacity to
	hold a request until the service can act on it; any extra simply spills over the edge and is discarded.
	Code implemented Via JVM based RateLimiter which is refilled by a scheduler. 
	
Rate Limiting is configured either following way in this project.
		1. Default property based value.
		2. User+API key based Value.

1. Default property based value
Properties defined to default refilling rate and interval with following properties:
  - ratelimit.default.refill.minutes To hold the default refill rate, right now set to 1 mins
  - ratelimit.defult.tokens  To hold default token for non DB configured combination value 1.

2. User+API key based Value

Table user_api_rule is used to store the user+APi based token information.
Structure is as follows:

CREATE TABLE user_api_rule (
user_api_id bigint AUTO_INCREMENT PRIMARY KEY,
user_id bigint NOT NULL,
api_id bigint NOT NULL,
token int default 0,
status int DEFAULT 1
);

Exceptions and APi Response:
For Following cases as per their respective entries:
HTTP Code MSg
429       TOO_MANY_REQUESTS And “Allowed-After-Mins=refillRate”
400       If userId invalid User_Id_not_found. Else “API_is_not_configured.”200
Success   with a string in body.

User Table and Entries:

User entries default provided using src/main/resources/data.sql
Table structure in src/main/resources/schema.sql:

CREATE TABLE user_detials (
user_id bigint AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(250) NOT NULL,
last_name VARCHAR(250) NOT NULL,
status int(4) DEFAULT 1
);

API Tables and Entries
API entries default provided using src/main/resources/data.sql
Table structure in src/main/resources/schema.sql:

CREATE TABLE api (
api_id bigint AUTO_INCREMENT PRIMARY KEY,
api_url VARCHAR(250) NOT NULL,
status int NOT NULL default 1
);
Technology Stack
Spring Boot
H2 DB

Scalability:
1.Current implementation is locking based and might bring some bottleneck if there are a lot of
requests coming from the same user and for the same API.
We can overcome this problem with using the redis in place of the local JVM based implementation.

2. We can create multiple nodes of the same configuration to scale since redis can work as a
centralized cache holder and lua script can offer the same atomicity as we are expecting with a single
node JVM based locking solution.

System Requirement and How to Configure

1. Configuration Requirement:
	  - JDK 1.8+
	  - Eclipse 4+
 
2. Import project using below steps in Eclipse:
    - Click File> Import
    - Type Maven in the search box > under Select an import source:
    - Select Existing Maven Projects
    - Click Next
    - Click Browse and select the folder that is the root of the Maven project
      (probably contains the pom.xml file)
    - Click Next and Finish
    
3. Running Application from Eclipse.
    - Select file RatelimitApplication.java
    - Run this file as java application.
  Without Eclipse :
    - Go to from source folder of project to ratelimit/target
    - Open command prompt and reach above location and run below command
    - java -jar -Dserver.contextPath=/ratelimit ratelimit-0.0.1-SNAPSHOT.jar
4. Testing the Functionality:
    - Install Postman.
    - Create a get request url as follows
      http://localhost:8080/ratelimit/api/v1/test
    - Add userId field in Request Header.
        Field: userId
