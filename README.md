# In Progress... NOT FINISHED!!!

# EV-Charging Station – API

This project implements a RESTful interface for the storage and retrieval of charging station data. It is built using the Spring Framework and Java, with MySQL as the database.

## Prerequisites

Before running the project, ensure you have the following installed on your system:

- Java Development Kit (JDK) version 8 or later
- Gradle
- MySQL Server
- Git (optional, if you want to clone the repository)

## Running the Project

Follow these steps to run the EV-Charging Station API:

1. **Clone the Repository (Optional)**

   If you haven't already, you can clone the repository using Git:

    ```bash
    git clone https://github.com/santush87/ev-charging-station-api.git
    ```

   Alternatively, you can download the source code as a ZIP archive and extract it to your desired location.

2. **Set Up MySQL Database**

    - Create a MySQL database for the project.
    - Configure the database connection in the `application.yml` file located in the `src/main/resources` directory. Replace the placeholder values with your MySQL database credentials.

3. **Build and Run the Project**

   Navigate to the project directory and build the project using Gradle:

    ```bash
    cd ev-charging-station-api
    gradle build
    ```

   This will compile the source code, run the unit tests, and package the application into a JAR file located in the `build/libs` directory.

   Once the build is successful, you can run the application using the following command:

    ```bash
    java -jar build/libs/ev-charging-station-api.jar
    ```

   This will start the Spring Boot application, and it will be accessible at `http://localhost:8080`.

## API Endpoints

The EV-Charging Station API provides the following endpoints:

- `GET /api/all`: Returns a list of all charging stations.
- `GET /api/charging-station/{unique_id}`: Returns the charging station with the given ID.
- `GET /api/charging-station?zipcode={zipcode}`: Returns the charging station(s) with the given zipcode.
- `GET /api/near-stations?lat={lat}&lon={lon}&distanceInMeters={distanceInMeters}`: Returns a list of charging stations within a specified distance from the given latitude and longitude.
- `POST /api/create`: Creates a new charging station.
- `DELETE /api/delete/{unique_id}`: Deletes the charging station with the given ID.

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
