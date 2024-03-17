FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY mvnw /app/mvnw
COPY .mvn /app/.mvn
COPY src /app/src
RUN mvn clean package -D skipTests

FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 8000
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]