# RateLimitSpring
Spring boot application with H2 DB to demonstrate the leaking bucket based implementation of Rate Limit.
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
