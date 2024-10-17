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

2. Copy the .env.example file to .env and fill in the values:

```bash
cp .env.example .env
```

3. Build the application:

```bash
./gradlew build
```

4. Run the application:

```bash
./gradlew run
```

5. Open your browser and navigate to `http://localhost:8080`.

6. Request a weather for a location, for example: `http://localhost:8080/weather/santiago`

7. Stop the application `CTRL+C`

8. Remove REDIS container:

```bash
docker-compose down
```
