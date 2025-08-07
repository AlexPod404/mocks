# ARMSB Mocks Multi-Module Project

This is a Maven multi-module project that consolidates 6 mock services for ARMSB (Automated Risk Management System Bank) into a single unified project structure.

## Project Structure

```
mocks/
├── pom.xml                    # Root aggregator POM
├── README.md                  # This file
├── armsb_calendar/            # Calendar service mock (port 8040)
│   ├── pom.xml
│   └── src/
├── armsb_client_card/         # Client card service mock (port 8010)
│   ├── pom.xml
│   └── src/
├── armsb_clients/             # Clients service mock (port 8001)
│   ├── pom.xml
│   └── src/
├── armsb_cti/                 # CTI service mock (port 8020)
│   ├── pom.xml
│   └── src/
├── armsb_tasks/               # Tasks service mock (port 8030)
│   ├── pom.xml
│   └── src/
└── assistant_sber_one/        # Sber One assistant mock (port 8050)
    ├── pom.xml
    └── src/
```

## Services and Ports

| Service | Port | Description |
|---------|------|-------------|
| armsb_calendar | 8040 | Calendar service mock |
| armsb_client_card | 8010 | Client card service mock |
| armsb_clients | 8001 | Clients service mock |
| armsb_cti | 8020 | CTI (Computer Telephony Integration) service mock |
| armsb_tasks | 8030 | Tasks service mock |
| assistant_sber_one | 8050 | Sber One assistant service mock |

## Technologies

- **Java**: 1.8
- **Spring Boot**: 2.6.4
- **Maven**: Multi-module project structure
- **Packaging**: Each service creates an executable JAR

## Building the Project

### Build All Services

To build all services at once:

```bash
mvn clean compile
```

To package all services (creates executable JARs):

```bash
mvn clean package
```

### Build Individual Services

To build a specific service:

```bash
# Build only the clients service
mvn clean package -pl armsb_clients

# Build only the calendar service  
mvn clean package -pl armsb_calendar

# Build only the client card service
mvn clean package -pl armsb_client_card

# Build only the CTI service
mvn clean package -pl armsb_cti

# Build only the tasks service
mvn clean package -pl armsb_tasks

# Build only the assistant service
mvn clean package -pl assistant_sber_one
```

## Running the Services

### Run Individual Services

Each service can be run independently using its executable JAR:

```bash
# Run calendar service (port 8040)
java -jar armsb_calendar/target/armsb-stubs-0.0.1.jar

# Run client card service (port 8010)
java -jar armsb_client_card/target/armsb_client_card-1.0.0.jar

# Run clients service (port 8001)
java -jar armsb_clients/target/armsb_clients-1.0.0.jar

# Run CTI service (port 8020)
java -jar armsb_cti/target/armsb_cti-1.0.0.jar

# Run tasks service (port 8030)
java -jar armsb_tasks/target/armsb_tasks-1.0.0.jar

# Run assistant service (port 8050)
java -jar assistant_sber_one/target/assistant_sber_one-1.0.0.jar
```

### Run Using Spring Boot Maven Plugin

Alternatively, you can run services directly using the Spring Boot Maven plugin:

```bash
# Run calendar service
mvn spring-boot:run -pl armsb_calendar

# Run client card service
mvn spring-boot:run -pl armsb_client_card

# Run clients service
mvn spring-boot:run -pl armsb_clients

# Run CTI service
mvn spring-boot:run -pl armsb_cti

# Run tasks service
mvn spring-boot:run -pl armsb_tasks

# Run assistant service
mvn spring-boot:run -pl assistant_sber_one
```

### Run All Services Simultaneously

To run all services at once, you can use separate terminal windows or background processes:

#### Using Background Processes (Linux/MacOS)

```bash
# Build all services first
mvn clean package

# Start all services in background
java -jar armsb_calendar/target/armsb-stubs-0.0.1.jar &
java -jar armsb_client_card/target/armsb_client_card-1.0.0.jar &
java -jar armsb_clients/target/armsb_clients-1.0.0.jar &
java -jar armsb_cti/target/armsb_cti-1.0.0.jar &
java -jar armsb_tasks/target/armsb_tasks-1.0.0.jar &
java -jar assistant_sber_one/target/assistant_sber_one-1.0.0.jar &

# Wait for all background jobs to complete
wait
```

#### Using Separate Terminal Windows

Open 6 separate terminal windows and run each service in its own window using the commands from the "Run Individual Services" section above.

## Stopping Services

To stop services running in the background:

```bash
# Find and kill all Java processes (be careful in production!)
pkill -f "java -jar.*armsb"
pkill -f "java -jar.*assistant_sber_one"

# Or stop specific services by finding their process IDs
ps aux | grep "java -jar"
kill <process_id>
```

## Configuration

Each service maintains its own configuration in `src/main/resources/application.properties`:

- **armsb_calendar**: `server.port=8040`, `spring.application.name=armsb-stubs`
- **armsb_client_card**: `server.port=8010`
- **armsb_clients**: `server.port=8001`
- **armsb_cti**: `server.port=8020`
- **armsb_tasks**: `server.port=8030`
- **assistant_sber_one**: `server.port=8050`

## Key Features

- **No Port Conflicts**: Each service runs on its own unique port
- **Independent Execution**: Each service has its own `@SpringBootApplication` entry point
- **Preserved Dependencies**: All original dependencies and configurations are maintained
- **Unified Build**: All services can be built together or individually
- **Simultaneous Operation**: All services can run at the same time without conflicts

## Development

Each service maintains its original:
- Controller paths and endpoints
- Business logic
- Dependencies and versions
- Spring Boot application configuration
- Test structure

The multi-module structure only adds the convenience of unified building and dependency management while preserving complete independence of each service.