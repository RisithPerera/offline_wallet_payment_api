# Stage 1: Build the builder image with JDK
FROM eclipse-temurin:21-jdk-noble AS builder

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

# Package application (tests already run in CI)
RUN ./mvnw clean package -DskipTests


# Stage 2: Build the runtime image with JRE
FROM eclipse-temurin:21-jre-alpine AS runtime

LABEL author="Risith Perera"

WORKDIR /service

# Copy only the executable JAR from the builder
COPY --from=builder /service/target/*.jar app.jar

# Expose the port (Spring default is 8080)
EXPOSE 8080

# Run the application as a non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

ENTRYPOINT ["java", "-jar", "app.jar"]