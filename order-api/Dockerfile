FROM adoptopenjdk:11-jre-hotspot
MAINTAINER caner.kaya
COPY target/order-api-0.0.1-SNAPSHOT.jar order-api-1.0.0.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "order-api-1.0.0.jar"]