# 1-qadam: Build stage (Maven bilan .jar faylni yaratish)
FROM maven:3.9.3-eclipse-temurin-17-alpine AS builder

WORKDIR /app

# Maven dependencies cache bo'lishi uchun pom.xml ni alohida COPY qilamiz
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# 2-qadam: Run stage (yaratilgan .jar ni ishga tushirish uchun)
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
