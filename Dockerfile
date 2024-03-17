FROM eclipse-temurin:17-jdk-jammy
RUN apt-get update && apt-get install -y mysql-client && apt-get install dos2unix && apt-get install nano

# Set the working directory in the container
WORKDIR /app

# Copy the project to the container so that we can build it there in a consistent way
COPY . .
COPY ./docker/entrypoint.sh /entrypoint.sh

# Build the project using Gradle
CMD chmod +x /app/gradlew
RUN /app/gradlew build -x test

CMD chmod +x+r /entrypoint.sh
RUN dos2unix /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh", "java", "-Dspring.config.location=file:/app/docker/application.yml", "-jar", "/app/build/libs/EV-ChargingStationAPI-0.0.1-SNAPSHOT.jar"]

# Uncomment to keep the container alive for debug purposes
#CMD ["tail", "-f", "/dev/null"]
