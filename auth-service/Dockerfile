# Use OpenJDK image from Docker hub
FROM openjdk:20

WORKDIR /

# Copy the compiled jar file to the docker image
COPY target/*.jar app.jar

# Copy the wait-for-it script to the docker image
COPY ./wait-for-it.sh ./wait-for-it.sh

RUN ls -l /

# Make the script executable
RUN ["chmod", "+x", "/wait-for-it.sh"]

# Use the wait-for-it script as the entrypoint to wait for eureka-server and then start the app
ENTRYPOINT ["/wait-for-it.sh", "http://eureka-server:8761", "--", "java", "-jar", "/app.jar"]