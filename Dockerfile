# Stage 1: Build the builder image with JDK
FROM eclipse-temurin:21-jdk-noble AS builder

LABEL author="Risith Perera"

# Create a working directory inside the container and navigate to it
WORKDIR /service

# Copy Maven wrapper, pom file and download dependencies
COPY mvnw ./
COPY .mvn/ .mvn/
COPY pom.xml ./
RUN chmod +x mvnw &&\
    ./mvnw dependency:go-offline

# Copy source code
COPY src/ src/

# Expose the port (Spring default is 8080)
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]