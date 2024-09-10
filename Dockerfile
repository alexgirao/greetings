
# docker image build -t greetings:latest .
# docker run --rm -d -p 8080:8080 greetings:latest

# FROM amazoncorretto:17
FROM openjdk:17-jdk-alpine

COPY spring-webflux/target/spring-webflux-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
