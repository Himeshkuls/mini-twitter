FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/tweet-service-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application.properties .
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/app/application.properties"]