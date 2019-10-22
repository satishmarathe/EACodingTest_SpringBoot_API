# EACodingTest_SpringBoot_API
This API is developed using Spring boot .
Swagger spec is being used to define the contract BUT NOT generate server side stubs.

Swagger.yml file is present in the root which provides details of the API


# Software versions required :
Maven 3.2.5+
JDK 1.8+
Spring Boot
Mockito

# To run this example:
First compile run unit tests package install  :
mvn clean install

To start rest service:
mvn spring-boot:run


This will start embedded tomcat on port : 9090
The API endpoint can then be accessed at : http://localhost:9090/api/v1/festivals

If this port is already in use please change the port in application.properties 


# Rate Limit Implementation
This API is expected to respond with a 429 ( Throttled / Rate Limit response ) 
The condition on when this response is to be generated in this contrived example is as follows :
We allow 3 requests to succeed and subsequent request will be served this 429 error response.
Then the counter will be reset to 0 to allow next subsequent 3 requests to succeed and so on ...
This is obviously a contrived and rather simple basic implementation.

# Notes on unit testing
API redeveloped using spring boot as spring facilitates mocking and testing quite easily
Unit Testing has been limited to mocking the service layer and unit testing the controller.
The same logic can be used to mock the service and data layer.


# Notes on code coverage 
Jacoco plugin included for measuring code coverage via unit tests.
To generate report run the command:
mvn test
Report is generated at : 
<project_root>\target\site\jacoco\index.html

# Note about CORS
In this example we have resolved cors issue ( for client consuming rest service ) by adding @CrossOrigin
annotation while in jersey implementation provided by swagger we had a Response filter adding to the header.