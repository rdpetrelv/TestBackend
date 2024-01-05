# Laundary Management Documentation
The Laundry Management System is a solution designed to optimise and simplify the operations of commercial, industrial or institutional laundries. It provides comprehensive tools to monitor machines, sensors and reserve management, with the objective of improving operational efficiency and user experience.

## Project structure
The project is divided into two repositories:
- **Backend:** Core logic in Java with Spring Boot [View Repository](https://github.com/rdpetrelv/TestBackend).
- **Frontend:** User interface with Vue, HTML and JavaScript. [View Repository](https://github.com/FatiGhaoui/LaundaryManagementFrontEnd.git)

### Prerequisites
- **Backend:**
    - Gradle 8.2.1
    - Java v17.0.8

- **Frontend:**
    - NodeJS v18.17.1
    - NPM v9.6.7

## Installation and Use

### Backend
```bash
git clone https://github.com/rdpetrelv/TestBackend
cd TestBackend
./gradlew build
```
Access the backend at [https://laundryapp.cleverapps.io/](https://laundryapp.cleverapps.io/swagger-ui/index.html) with the credentials:
```
user
myPassword
````

### Frontend
```bash
git clone https://github.com/FatiGhaoui/LaundaryManagementFrontEnd.git
cd LaundaryManagementFrontEnd
npm install
npm run dev
```
Use the frontend in [http://localhost:5173/](http://localhost:5173/).

## Tutorials
The following are examples of how to interact with the system through its APIs:

#### Create a Reservation

```http
POST /api/reservation
Content-Type: application/json

{
  "reservationUser": "NombreUsuario",
  "reservationDate": "2024-01-01",
  "reservationStartTime": "08:00",
  "reservationEndTime": "10:00"
}
```

#### Get all Reservations

```http
GET /api/reservation
```

#### Update the Status of a Machine

```http
PUT /api/machines/updateAvailable/{id}
```

## Sample Projects
Within the project repository, sample tests are included that demonstrate how key functionality can be interacted with using automated tests. These examples are designed to show the practical implementation of unit and integration testing using the Java test library.

#### 1. `ReservationDaoTest`.

This test file, `ReservationDaoTest`, illustrates how to test the functionality of the Reservation DAO (`ReservationDao`). This test focuses on searching for a reservation by its ID and verifies that the specific details of the retrieved reservation match the expected values.

```java
@DataJpaTest
public class ReservationDaoTest {

    @Autowired
    private ReservationDao reservationDao;

    /**
     * Tests the functionality to find a reservation by ID.
     * verifies that specific details of the retrieved reservation match the expected values.
     */
    @Test
    public void shouldFindAnyReservationById(){
        // Retrieve a reservation using the Dao method
        ReservationEntity reservation = reservationDao.getReferenceById(-3L);
        // Assert specific details of the retrieved reservation
        Assertions.assertThat(reservation.getReservationUser()).isEqualTo("TEST RESERVATION");
        Assertions.assertThat(reservation.getReservationAvailable()).isEqualTo(false);
    }
}
```

#### 2. `ReservationIntegration`.

The `ReservationIntegration` file contains integration tests that simulate interactions with the `Reservation` endpoints via HTTP requests. These tests validate the behaviour of the endpoints in different scenarios, including authentication, response types and verification of retrieved data.

```java
public class ReservationIntegration {

    final String auth = "user" + ":" + "myPassword";
    final byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
    final String authHeader = "Basic " + new String(encodedAuth);

    @Test
    public void givenPath_whenAccessedWithoutAuthentication_then401IsReceived() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/reservation/-500");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        Assertions.assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void givenReservationByIdAccessed_whenReservationInfoRetrieved_then200IsReceived() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/reservation/-500");

        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        Assertions.assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson() throws ClientProtocolException, IOException {
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet( "http://localhost:8080/api/reservation" );
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        Assertions.assertThat(jsonMimeType).isEqualTo( mimeType );
    }

    @Test
    public void givenReservationExists_whenReservationIsRetrieved_thenRetrievedResourceIsCorrect() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/reservation/-3");
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ReservationEntity reservationRetrieved = mapper.readValue(jsonFromResponse, ReservationEntity.class);        Assertions.assertThat(reservationRetrieved.getReservationUser()).isEqualTo("TEST RESERVATION");
    }
}
```

## Key Components

### Machines
The machines component manages the status and operations of washing machines and dryers. It contains:
- `MachineController`: Controller that handles CRUD operations of machines.
- `MachineEntity`: Entity representing a machine in the database.
- `MachineRecord`: Record for transferring machine data between layers.
- `MachineDao`: DAO interface for machine operations in the database.
- `MachineMapper`: Mapper for converting machine entities to records and vice versa.

### Sensors
The sensors component is responsible for the monitoring of sensors on the machines. It includes:
- `SensorController`: Controller for sensor related operations.
- `SensorEntity`: Entity representing a sensor in the database.
- `SensorRecord`: Record for transferring sensor data between layers.
- `SensorDao`: DAO interface for sensor operations in the database.
- `SensorMapper`: Mapper for converting sensor entities to records and vice versa.

### Reservations
The reservations component is responsible for managing reservations of washing machines and tumble dryers. It is composed of:
- `ReservationController`: Controller that handles the CRUD operations of reservations.
- `ReservationEntity`: Entity that represents a reservation in the database.
- `ReservationRecord`: Record for transferring reservation data between layers.
- `ReservationDao`: DAO interface for reservation operations in the database.
- `ReservationMapper`: Mapper for converting reservation entities to records and vice versa.

### Interactions between Entities
- The `MachineEntity` and `SensorEntity` entities are strongly linked, allowing accurate monitoring of machine status through associated sensors.
- Reservations `ReservationEntity` are connected to the machine entities, allowing efficient management of machine availability.

## System Architecture

### Code structure

The project follows a modular structure divided into different packages:

- `com.laundry.laundryMgmt.controllers`: Contains controllers for each entity in the system.
- `com.laundry.laundryMgmt.dao`: DAO interfaces for the interaction with the database.
- `com.laundry.laundryMgmt.models`: Models representing entities in the database.
- `com.laundry.laundryMgmt.records`: Records used to transfer data between layers.
- `com.laundry.laundryMgmt.mappers`: Mappers to convert entities to records and vice versa.
- `com.laundry.laundryMgmt.businessLogic`: Business logic and system services.

### Interaction between layers
The system follows an MVC (Model-View-Controller) architecture pattern, where controllers act as an entry point, interacting with services, mappers and data access layer to handle user requests.

## Contributions and Support

- Contributions: Open an issue to discuss improvements.
- License: MIT
- Authors: Angela GALEANO, David PETREL, Fatima GHAOUI
- Support: [support@laundrymgmt.com](mailto:support@laundrymgmt.com)