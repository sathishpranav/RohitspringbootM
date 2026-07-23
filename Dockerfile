FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app
COPY target/helloworld-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]

