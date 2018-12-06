FROM gradle:alpine as builder
USER root
ADD . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle --stacktrace assemble

FROM openjdk:8-jre-alpine
COPY --from=0 /home/gradle/project/build/libs/app.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
