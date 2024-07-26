# Requirement
In our database, we are storing phone numbers associated with customers and will provide an interface to manage it. Assume phone numbers as a static data structure. Develop API with the below capabilities.
- Get all phone number
- Get phone numbers of a single customer
- activate a phone number

# Whats in the code
1. Open API spec as per the requirements. Available src/main/resources/openapi.yaml.
2. Secure endpoint using basic Spring security.
3. Actuator for health check and other details.
4. Swagger codegen was used generate code including controller, delegate and models.
5. Lombok is used for DTO.
6. MapStruct was used to map DTO to Response.
7. Spring Validator used to validate business requirements. Could externalise the message.
8. Service classes were used to do the business logic
9. Exception Handler was used standardise the exception handling
10. Integration tests using MockMvc
11. Added docker for consistency and portability.
12. Validated GET requests based on authenticated user and PUT request based on the role of authenticated user.

## Design considerations

1. When admin retrieves of all phone numbers they would like to know the customer to whom those numbers belong.
2. Need to design with a customer id to get a customers details.

## To run

1. ```./gradlew bootRun.``` If you get access denied, run chmod 755 gradlew
2. To get debug logs, in resources/application.properties, please set logging.level.com.belong.customer to DEBUG
3. For API calls, in postman.
   ```  
   GET http://localhost:8080/api/v1/customers/contactDetails
   with basic authentication credentials (admin1/admin1)
   ```
   ```
   GET http://localhost:8080/api/v1/customers/Joe1/contactDetails
   with basic authentication credentials (Joe1/Joe1)
   ```
   ```
   PUT http://localhost:8080/api/v1/customers/Joe1/contactDetail
   Content-Type header as application/json in Header tab
   with basic authentication credentials (admin1/admin1)
   {
    "phoneNumber" : "0400000000",
    "active" : true
   }
   ```
   ```
   GET http://localhost:8080/actuator/health
   ```

4. To start docker container for the springboot service (prerequisite: docker installed), first make sure local sprint boot server is not running so that port 8080 is available. Then go to the root directory run
   ```docker-compose up --build```
5. I have introduced lombok package, so Intellij may need this setup to avoid compilation error.
   Go to Inteiij Preferences > search for annotation processor and check enable annotationprocessing


## What could have been done better?
1. Add pagination for retrieving all customer data
2. Add Tracing, profile specific logging for prod and easy log access for support
3. Add more unit testing. Please refer PhoneValidatorTest, PhoneServiceTest as a sample
4. Externalised error messages
5. Linting