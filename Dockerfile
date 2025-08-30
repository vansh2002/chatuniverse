# Step 1: Build the app with Maven
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

# Copy all files to container
COPY . .

# Build the Spring Boot app (skip tests for faster build)
RUN mvn clean install -DskipTests

# Step 2: Run the app using OpenJDK
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app runs on (default 8080)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
