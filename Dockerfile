FROM openjdk:12-jdk-alpine
COPY target/demo-0.0.1-SNAPSHOT.jar /spring-test.jar
CMD ["java", "-jar", "-Dspring.profiles.active=default", "/spring-test.jar"]