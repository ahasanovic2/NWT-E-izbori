FROM openjdk:20 as builder
COPY target/*.jar app.
ENTRYPOINT ["java","-jar","/app.jar"]