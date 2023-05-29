# Use a multi-stage build to reduce the final image size
FROM maven:3.8.5-openjdk-17 AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml file and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src/ /app/src/
RUN mvn clean package

# Use a smaller base image for the final image
FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/api**.jar /app

# Expose port 8080
EXPOSE 8888

# Run the application
CMD ["java", "-jar", "api**.jar"]