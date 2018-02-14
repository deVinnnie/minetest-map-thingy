FROM openjdk:8-jdk-alpine
COPY build/libs/app-0.0.1.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]