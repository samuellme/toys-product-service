# Toy Service [![Build Status](https://github.com/SiamandMaroufi/toys-produc-service/workflows/Java%20CI%20with%20Maven/badge.svg)](https://github.com/SiamandMaroufi/toys-produc-service/actions)


This service can be used for serving a list of products via a rest call.

## Endpoints

 |Method|Endpoint|Descriptio|
 |------|-------|-----|
 |**GET**|http://localhost:8080/product| list of the products|
 |**GET**|http://localhost:8080/| Swagger UI |
 |**GET**|http://localhost:8080/api-docs| Open API specification |
 |**GET**|http://localhost:8080/actuator/health| health endpoint |
 |**GET**|http://localhost:8080/actuator/info| service information |
 |**GET**|http://localhost:8080/actuator/metrics| service metrics |
 
## How To

### Quick start

for having a quick start, you can easily use the `startup.sh` script, it uses the docker; 
therefore, you should have docker installed.

```
$ ./startup.sh
```

#### Manual run (Developing)
if you  want to run it manually without taking advantage of docker, you can 
run a redis-server locally then build and run via maven as the following:
```
$ redis-server &
$ mvn clean install
$ java -jar target/toys-0.0.1.jar
``` 

you can also use the MavenWrapper and the springboot plug in for running it:
```
$ redis-server &
$ ./mvnw spring-boot:run  
```

 ### Run tests
 
 for running tests you can use mvn:
 
 ```
$ mvn clean verify
```

or (via maven wrapper)

```
$ ./mvnw clean verify
```

