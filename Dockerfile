FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /tmp

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:21-jdk-alpine
LABEL authors="andsv"
WORKDIR /app
COPY --from=build /tmp/target/api-lojas-0.0.1-SNAPSHOT.jar ./api-lojas.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","api-lojas.jar"]