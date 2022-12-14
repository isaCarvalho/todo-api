FROM openjdk:15-jdk-alpine
ARG JAR_FILE=build/libs/todo-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]