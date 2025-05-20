# Base image
FROM eclipse-temurin:17-jdk-alpine

# App folder
WORKDIR /app

# Jar faylni nusxa olish
COPY target/ecommerce-app.jar app.jar

# Port ochish
EXPOSE 8080

# Appni ishga tushirish
ENTRYPOINT ["java", "-jar", "app.jar"]