FROM adoptopenjdk:11-jre-hotspot
MAINTAINER caner.kaya
COPY target/authorization-api-0.0.1-SNAPSHOT.jar authorization-api-1.0.0.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "authorization-api-1.0.0.jar"]