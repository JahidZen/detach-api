# Stage 1: Build
FROM gradle:9.4.1-jdk21-alpine AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test


#Stage 2: Run
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]