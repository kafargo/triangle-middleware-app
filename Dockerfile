# Use an official OpenJDK runtime as a parent image
FROM openjdk:25-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build output (JAR file) into the container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Set the environment variable
ENV DISABLE_BROWSER=true

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]