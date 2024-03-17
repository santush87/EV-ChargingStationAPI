# In Progress... NOT FINISHED!!!

# EV-Charging Station – API

This project implements a RESTful interface for the storage and retrieval of charging station data. It is built using the Spring Framework and Java, with MySQL as the database.

## Prerequisites

Before running the project, ensure you have the following installed on your system:

- Java Development Kit (JDK) version 17 or later
- Gradle
- MySQL Server
- Git (optional, if you want to clone the repository)

## Running the Project

Follow these steps to run the EV-Charging Station API:

1. **Clone the Repository (Optional)**

   If you haven't already, you can clone the repository using Git:

    ```bash
    git clone https://github.com/santush87/EV-ChargingStationAPI.git
    ```

   Alternatively, you can download the source code as a ZIP archive and extract it to your desired location.

2. **Set Up MySQL Database**

    - Create a MySQL database for the project.
    - Configure the database connection in the `application.yml` file located in the `src/main/resources` directory. Replace the placeholder values with your MySQL database credentials.

3. **Build and Run the Project**

   Navigate to the project directory and build the project using Gradle:

    ```bash
    cd EV-ChargingStationAPI
    gradle build -x test
    ```

   This will compile the source code, and package the application into a JAR file located in the `build/libs` directory.

   Once the build is successful, you can run the application using the following command:

    ```bash
    java -jar build/libs/EV-ChargingStationAPI.jar -Dspring.config.location=file:/app/application.yml
    ```

   This will start the Spring Boot application, and it will be accessible at `http://localhost:8080`.

## Running the tests
Currently, the tests need a working MySQL database, that is required due to the dependency of geospatial data types 
and indexes. We can use in memory MariaDB implementation, but for the purpose of this project we use a mysql database 
via docker.

```bash
docker compose up mysql_test
gradle test
```
## Running the Project with Docker

### Prerequisites
- JDK 17 or higher installed
- Docker installed

### Instructions
1. **Build the project:**
   - For Windows:
     ```
     .\gradlew.bat build -x test
     ```
   - For Linux:
     ```
     gradle build -x test
     ```

2. **Create a custom network:**
    ```
    docker network create ev_network
    ```

3. **Run the Docker containers:**
    ```
    docker compose up
    ```

4. **After running the Docker containers, you can optionally run the test suite:**
   - **Start the database container for tests:**
     ```
     docker compose up mysql_test
     ```
   - For Windows:
     ```
     .\gradlew.bat test
     ```
   - For Linux:
     ```
     gradle test
     ```

### Postman Collection
To test the functionalities of the API, import the provided Postman collection file located in the `doc` folder:
[`/docs/Charging Stations API.postman_collection.json`.](/docs/Charging%20stations%20API.postman_collection.json)

## Note
- Ensure that ports specified in the Docker configuration are available and not in use by any other application.
- The provided Postman collection contains examples of API requests to try out the functionalities.

## API Endpoints

Each charging station in this API is characterized by a unique ID, Geo-Coordinates (latitude, longitude), and zipcode/postcode. 
Internally the search by distance is done using the MySQL geospatial functionality.
The query speed could be improved by adding spatial index to the point column as a future improvement.

The EV-Charging Station API provides the following endpoints:

- `GET /api/all`: Returns a list of all charging stations.
- `GET /api/charging-station/{unique_id}`: Returns the charging station with the given ID.
- `GET /api/charging-station?zipcode={zipcode}`: Returns the charging station(s) with the given zipcode.
- `GET /api/near-stations?lat={lat}&lon={lon}&distanceInMeters={distanceInMeters}`: Returns a list of charging stations within a specified distance from the given latitude and longitude.
- `POST /api/create`: Creates a new charging station.
- `DELETE /api/charging-station/delete?uniqueId={unique_id}`: Deletes the charging station with the given ID.

## Postman collection

You can find postman collection [here](/docs/Charging%20stations%20API.postman_collection.json)

## Testing

Unit tests have been included in the project with a coverage of 80%. You can run the tests using Gradle:

```bash
gradle test
```

## Additional Notes

- Make sure to configure the application.yml file to set up your database connection and other configurations as required.
- This README assumes default configurations. Modify port numbers or any other configurations if you have made changes in the application.

## Support

If you encounter any issues or have questions regarding the EV-Charging Station API, please feel free to contact [martin.aleksandrov87@gmail.com](mailto:your-email@example.com).

Enjoy using the EV-Charging Station API!
