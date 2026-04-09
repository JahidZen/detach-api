# Stage 1: Build
FROM gradle:8.2-alpine-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle clean buid -x test


#Stage 2: Run
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]