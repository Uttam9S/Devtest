# Use OpenJDK as the base image
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the built JAR file
COPY target/webapp-1.0.0.jar app.jar


# Command to run the application
CMD ["java", "-jar", "app.jar"]

