FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY build/libs/laundryMgmt-0.0.1-SNAPSHOT.jar app.jar
ENV APP_PORT 8080
EXPOSE ${APP_PORT}
CMD ["sh", "-c", "java -jar -Dserver.port=${APP_PORT} /app.jar"]