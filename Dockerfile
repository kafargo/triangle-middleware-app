# Stage 1: Build the application using Maven
FROM maven:3-openjdk-17 AS build

# Set the working directory for the build
WORKDIR /app

# Copy the source code into the container
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Use Amazon Corretto to run the application
FROM amazoncorretto:17-alpine

# Set the working directory for the runtime
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Install xdg-utils for opening URLs
RUN apk update && apk add xdg-utils

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]