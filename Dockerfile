# Start with a base image that has Java
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /stock-api-service

# Set environment variables (you can replace these with real values or pass them at runtime)
# ENV DB_URL=jdbc:mysql://192.168.128.5:3306/stock
# ENV DB_URL=jdbc:mysql://172.17.0.2:3306/stock
ENV DB_URL=jdbc:mysql://mysql-container:3306/stock
ENV DB_USERNAME=root
ENV DB_PASSWORD=root
ENV CORS_URL=http://localhost:4200

# Copy the JAR file built by Maven or Gradle into the container
COPY stock-api-service/build/libs/stock-api-service-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot app will run on
EXPOSE 8090

# Set the command to run your Spring Boot application
# ENTRYPOINT ["java", "-jar", "/stock-api-service/stock-api-service.jar"]
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
