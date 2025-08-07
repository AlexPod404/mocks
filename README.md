# Mocks Multi-Module Project

This is a Maven multi-module project containing 6 Spring Boot mock services that can be run simultaneously on different ports.

## Architecture

The project is structured as a Maven multi-module project with the following hierarchy:

```
mocks-parent/
├── pom.xml (parent POM with packaging=pom)
├── armsb_clients/
├── armsb_client_card/
├── armsb_cti/
├── armsb_tasks/
├── armsb_calendar/
├── assistant_sber_one/
└── README.md
```

### Technical Stack
- **Java**: 1.8
- **Spring Boot**: 2.6.4
- **Maven**: Multi-module project structure
- **Dependencies**: Spring Web, Commons IO, Lombok, Commons Lang3

### Port Configuration
Each mock service runs on a dedicated port to allow parallel execution:

| Service | Port | Description |
|---------|------|-------------|
| armsb_clients | 8001 | Client management service |
| armsb_client_card | 8010 | Client card service |
| armsb_cti | 8020 | CTI (Computer Telephony Integration) service |
| armsb_tasks | 8030 | Task management service |
| armsb_calendar | 8040 | Calendar service |
| assistant_sber_one | 8050 | Sber assistant service |

## Building the Project

### Build All Modules
```bash
mvn clean compile
```

### Package All Modules
```bash
mvn clean package
```

### Build Specific Module
```bash
cd armsb_clients
mvn clean package
```

## Running the Services

### Running All Services Simultaneously

You can run all services in parallel using separate terminal windows/tabs:

**Terminal 1 - armsb_clients (port 8001):**
```bash
cd armsb_clients
mvn spring-boot:run
```

**Terminal 2 - armsb_client_card (port 8010):**
```bash
cd armsb_client_card
mvn spring-boot:run
```

**Terminal 3 - armsb_cti (port 8020):**
```bash
cd armsb_cti
mvn spring-boot:run
```

**Terminal 4 - armsb_tasks (port 8030):**
```bash
cd armsb_tasks
mvn spring-boot:run
```

**Terminal 5 - armsb_calendar (port 8040):**
```bash
cd armsb_calendar
mvn spring-boot:run
```

**Terminal 6 - assistant_sber_one (port 8050):**
```bash
cd assistant_sber_one
mvn spring-boot:run
```

### Running with JAR Files

Alternatively, you can package and run the JAR files:

```bash
# Package all modules first
mvn clean package

# Then run each service
java -jar armsb_clients/target/armsb_clients-1.0.0.jar &
java -jar armsb_client_card/target/armsb_client_card-1.0.0.jar &
java -jar armsb_cti/target/armsb_cti-1.0.0.jar &
java -jar armsb_tasks/target/armsb_tasks-1.0.0.jar &
java -jar armsb_calendar/target/armsb_calendar-1.0.0.jar &
java -jar assistant_sber_one/target/assistant_sber_one-1.0.0.jar &
```

## Testing the Services

### Health Check
Once all services are running, you can verify they are accessible:

```bash
# Check armsb_clients (port 8001)
curl http://localhost:8001/actuator/health
# OR if no actuator endpoint exists:
curl -I http://localhost:8001

# Check armsb_client_card (port 8010)
curl http://localhost:8010/actuator/health
curl -I http://localhost:8010

# Check armsb_cti (port 8020)
curl http://localhost:8020/actuator/health
curl -I http://localhost:8020

# Check armsb_tasks (port 8030)
curl http://localhost:8030/actuator/health
curl -I http://localhost:8030

# Check armsb_calendar (port 8040)
curl http://localhost:8040/actuator/health
curl -I http://localhost:8040

# Check assistant_sber_one (port 8050)
curl http://localhost:8050/actuator/health
curl -I http://localhost:8050
```

### Service-Specific Endpoints

Each service has its own controllers and endpoints. Common patterns to test:

**armsb_clients (8001):**
```bash
curl http://localhost:8001/api/clients
curl http://localhost:8001/clients
```

**armsb_client_card (8010):**
```bash
curl http://localhost:8010/api/client-card
curl http://localhost:8010/card
```

**armsb_cti (8020):**
```bash
curl http://localhost:8020/api/cti
curl http://localhost:8020/cti
```

**armsb_tasks (8030):**
```bash
curl http://localhost:8030/api/tasks
curl http://localhost:8030/tasks
```

**armsb_calendar (8040):**
```bash
curl http://localhost:8040/api/calendar
curl http://localhost:8040/calendar
```

**assistant_sber_one (8050):**
```bash
curl http://localhost:8050/api/assistant
curl http://localhost:8050/assistant
```

## Development

### Adding Dependencies
To add dependencies to a specific module, edit the respective `pom.xml` file in the module directory. Common properties and dependency versions are managed in the parent POM.

### Modifying Business Logic
Each service maintains its original business logic and controller structure. To modify:
1. Navigate to the specific service directory
2. Edit files in `src/main/java/`
3. The main application class with `@SpringBootApplication` annotation should not be modified

### Port Configuration
Ports are configured in each service's `application.properties` file:
- `armsb_clients/src/main/resources/application.properties`
- `armsb_client_card/src/main/resources/application.properties`
- etc.

## Troubleshooting

### Port Conflicts
If you encounter port conflicts:
1. Check which process is using the port: `netstat -an | grep :8001`
2. Kill the process if needed: `kill -9 <PID>`
3. Or modify the port in the respective `application.properties` file

### Build Issues
If you encounter build issues:
1. Clean and rebuild: `mvn clean compile`
2. Check Java version: `java -version` (should be 1.8+)
3. Check Maven version: `mvn -version`

### Memory Issues
If running all 6 services simultaneously causes memory issues:
1. Increase JVM heap size: `java -Xmx512m -jar service.jar`
2. Run services in batches instead of all at once
3. Monitor system resources with `top` or `htop`

## Project Structure

Each submodule maintains its original structure:
```
<service-name>/
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── sb1/stub/<service>/
│       │       ├── Application.java
│       │       └── [other Java files]
│       └── resources/
│           └── application.properties
└── target/ (generated after build)
```

The parent POM manages common configurations, dependencies, and plugin versions while each submodule retains its specific business logic and configurations.