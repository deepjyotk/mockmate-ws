# Stage 1: Build Stage
FROM --platform=$BUILDPLATFORM gradle:8.4-jdk21 AS build
WORKDIR /app

# Copy Gradle build files and source code to the container
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# Run the Gradle build (create the JAR)
RUN gradle clean build -x test

# Stage 2: Run Stage
FROM --platform=$TARGETPLATFORM amazoncorretto:21.0.4-alpine
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Copy the .env.docker file into the container
COPY .env /app/.env

# Expose the port your application will run on
EXPOSE 9090

# Set the entry point to run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]