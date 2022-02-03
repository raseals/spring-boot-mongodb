# Spring Boot MongoDB Example
This project is a simple example of using Spring Boot with a MongoDB.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-mongodb)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)

## Requirements
The following are system requirements:

* Java 11 JDK
* Apache Maven
* MongoDB

## MongoDB Docker Setup
This project is using the latest MongoDB 5.0
```
docker pull mongo:5.0.6
```

### MongoDB Docker Runtime Container
```
docker run --name mongodb -d \
  -p 27017:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=mongo \
  -e MONGO_INITDB_ROOT_PASSWORD=mongo \
  -v /my/own/datadir:/data/db \
  mongo:5.0.6
```

## Build Instructions
```
mvn clean compile
```

## Packaging
```
mvn clean package
```

## Java command-line
In order to start the application, you must have all the necessary system requirements and environment variables
configured. The ``src/main/resources/application.properties`` file within this project has a listing of all environment variables
with default values set.

```
java -Duser.timezone="UTC" \
  -jar spring-boot-mongodb-0.0.1.jar \
  --spring.data.mongodb.uri=mongodb://mongo:mongo@localhost:27017 \
  --spring.data.mongodb.database=mygrocerylist
```

## Application Install Docker Image
The Dockerfile is located in the 'root' module directory; therefore,
if you want to use the Maven dockerfile plugin to install the Docker image,
you must be in the 'root' directory.

Before you try and install the Docker image, you must first successfully
Package the application using the "Packaging" phase, as described above.

The Docker Image prefix can be changed in the ``pom.xml`` file.

```
mvn dockerfile:build
```

### Application Runtime Docker Container
```
docker run --name spring-boot-mongodb -d \
  -p 8080:8080 \
  -e spring.data.mongodb.uri=mongodb://mongo:mongo@localhost:27017 \
  -e spring.data.mongodb.database=mygrocerylist \
  foobar/spring-boot-mongodb:0.0.1
```

## Simple REST Controller to verify working application
I created a simple REST controller with a single GET method to perform simple CRUD operations on the MongoDB.
To execute the REST controller, use a web browser and type:
```
http://localhost:8080/spring-boot-mongodb/grocery
```

Log output is to console stdout.
