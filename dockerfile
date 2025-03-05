# Stage 1: Build the Maven project with Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Package the application without running tests
RUN mvn clean package -DskipTests

# Stage 2: Create a minimal runtime image
FROM eclipse-temurin:17-jre

# Set the working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/file-encryption-utility-1.0.0.jar app.jar

# Run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
