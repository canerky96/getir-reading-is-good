FROM adoptopenjdk:11-jre-hotspot
MAINTAINER caner.kaya
COPY target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway-1.0.0.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "api-gateway-1.0.0.jar"]