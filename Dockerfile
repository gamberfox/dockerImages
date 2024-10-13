# Start with a base image that has Java
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file built by Maven or Gradle into the container
COPY target/myapp-0.0.1-SNAPSHOT.jar /app/myapp.jar

# Expose the port your Spring Boot app will run on
EXPOSE 8090

# Set the command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]
