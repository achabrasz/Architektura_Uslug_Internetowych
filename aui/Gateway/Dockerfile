FROM eclipse-temurin:17
WORKDIR /app
ARG JAR_FILE=target/Gateway-3.1.5.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app.jar"]
