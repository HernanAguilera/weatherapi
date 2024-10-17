# Weather API

## Getting Started

### Prerequisites

- JDK 11
- Docker

### Running the Application

1. Run docker-compose in root directory to start REDIS container:

```bash
docker-compose up -d
```

2.Build the application:

```bash
./gradlew build
```

3. Run the application:

```bash
./gradlew run
```

3. Open your browser and navigate to `http://localhost:8080`.

4. Request a weather for a location, for example: `http://localhost:8080/weather/santiago`

5. Stop the application `CTRL+C`

6. Remove REDIS container:

```bash
docker-compose down
```
