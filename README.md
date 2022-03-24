# Getir Tech Chalange

## Architectural Design

![Design@2x](https://user-images.githubusercontent.com/33653098/159524706-67c9c765-300c-48ff-8973-5a7a47071523.png)

## Basic explanations about capabilities

### Anyone

- Create customer account

### Customer

- Query the all orders of customer
- Create a new order
- Query an order by id
- List orders by date interval
- Query monthly statistics

### Admin

In addition to the customer's permissions, admin can also perform the following operations.

- Create new book
- Update the stock of book

## Running instructions on local machine

While First, we need to run MongoDB and Redis on docker machine. Go to project directory and type:

```
docker-compose up --build -d redis mongo
```

Now we need to build packages

```
mvn -f api-gateway clean test package
mvn -f authorization-api clean test package
mvn -f order-api clean test package
```

Finaly we can run jar files

```
java -jar api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar
java -jar authorization-api/target/authorization-api-0.0.1-SNAPSHOT.jar
java -jar order-api/target/order-api-0.0.1-SNAPSHOT.jar
```

## Running instructions on docker

Before creating docker images, we should build the projects. So go project path and type:

```
mvn -f api-gateway clean test package
mvn -f authorization-api clean test package
mvn -f order-api clean test package
```

Now we can build and run our docker images.

```
docker-compose up --build -d
```

## How to use?

While authorization-api running up `ADMIN` will be created with username `admin` and password `admin`. Before each
request you need the set Authorization header except of creating user.
You can see the details in Postman Collection (`Getir Tech Challenge.postman_collection.json`).

## Tech Stack

- Spring Boot
- Spring Data JPA
- Spring Validation
- Spring Cloud Security
- Spring Cloud Zuul Gateway
- Spring Cloud Oath2.0
- H2 Database
- Redis
- MongoDB
- Lombok
- MapStruct
- Flyway
- SwaggerUI
- OpenFeign
- QueryDSL
- JUnit5