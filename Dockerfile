FROM eclipse-temurin:17-jdk-jammy
RUN apt-get update && apt-get install -y mysql-client

COPY /build/libs/EV-ChargingStationAPI-0.0.1-SNAPSHOT.jar app.jar
COPY ./docker/entrypoint.sh /app/entrypoint.sh

CMD chmod +x /app/entrypoint.sh

ENTRYPOINT ["/app/entrypoint.sh", "mysql", "java", "-jar", "/app.jar", " -Dspring.config.location=file:/app/application.yml"]
