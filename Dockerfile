FROM openjdk:17-alpine

WORKDIR /app

COPY target/*.jar ./app

ENTRYPOINT java -jar app
