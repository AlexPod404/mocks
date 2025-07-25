# ARMSB Mock Services - Consolidated Application

This project consolidates all ARMSB mock services into a single Spring Boot application providing unified API endpoints for all ARMSB subsystems.

## Overview

The ARMSB Mock application provides consolidated mock services for:
- **Clients subsystem** - Client management and operations
- **Tasks subsystem** - Task management and workflow operations  
- **CTI subsystem** - Computer Telephony Integration services
- **Client Card subsystem** - Client card management and data operations

## Features

- ✅ **Spring Boot 2.6.4** with Java 8 compatibility
- ✅ **Spring Boot Actuator** for metrics and monitoring
- ✅ **Swagger/OpenAPI 3** documentation at `/swagger-ui.html`
- ✅ **Centralized logging** with configurable levels via environment variables
- ✅ **Configurable delays** for all mock responses
- ✅ **Conditional enabling/disabling** of subsystem controllers
- ✅ **Comprehensive endpoint coverage** migrated from original services

## Quick Start

### Build and Run

```bash
# Build the application
mvn clean package

# Run the application
java -jar target/armsb-mock-1.0.0.jar

# Application will start on port 8080
```

### Configuration

Configure the application using environment variables or `application.yaml`:

```yaml
server:
  port: 8080

armsb:
  mock:
    default-delay: 100  # Default delay in milliseconds
    delays:
      # Per-endpoint delay configuration
      "/clients/srvgetclientlist": 200
      "/tasks/getByFilter": 150
      "/cti/getCommunications": 300
    clients:
      enabled: true
    tasks:
      enabled: true
    cti:
      enabled: true
    client-card:
      enabled: true

logging:
  level:
    root: INFO
    sb1.stub.armsbmock: DEBUG
```

### Environment Variables

- `SERVER_PORT` - Server port (default: 8080)
- `ARMSB_DEFAULT_DELAY` - Default response delay in ms (default: 100)
- `LOG_LEVEL_ROOT` - Root logging level (default: INFO)
- `LOG_LEVEL_ARMSB` - ARMSB components logging level (default: DEBUG)
- `ARMSB_CLIENTS_ENABLED` - Enable/disable Clients controller (default: true)
- `ARMSB_TASKS_ENABLED` - Enable/disable Tasks controller (default: true)
- `ARMSB_CTI_ENABLED` - Enable/disable CTI controller (default: true)
- `ARMSB_CLIENT_CARD_ENABLED` - Enable/disable Client Card controller (default: true)

## API Documentation

### Swagger UI
Access interactive API documentation at: `http://localhost:8080/swagger-ui.html`

### OpenAPI Specification
Raw OpenAPI JSON available at: `http://localhost:8080/v3/api-docs`

## Monitoring and Metrics

### Actuator Endpoints
Spring Boot Actuator provides monitoring endpoints at `/actuator`:

- `/actuator/health` - Application health status
- `/actuator/info` - Application information
- `/actuator/metrics` - Application metrics
- `/actuator/env` - Environment properties
- `/actuator/loggers` - Logging configuration

## Delay Configuration

The ARMSB Mock application provides comprehensive delay management functionality for all endpoints. Delays can be configured both globally and per-endpoint, with changes persisted to the `application.yaml` configuration file.

### Configuration Methods

You can configure delays in three ways:
1. **Static configuration** in `application.yaml`
2. **Dynamic API configuration** using the DelayController (recommended)
3. **Legacy API configuration** using ConfigurationController

### Static Configuration (application.yaml)

Configure default delays and specific endpoint delays in the configuration file:

```yaml
armsb:
  mock:
    default-delay: 100  # Default delay in milliseconds
    delays:
      # Per-endpoint delay configuration
      "/clients/srvgetclientlist": 200
      "/tasks/getByFilter": 150
      "/cti/getCommunications": 300
      "/sbpemployeeinfo/v1/employee": 50
```

### Dynamic API Configuration (DelayController)

The DelayController provides a RESTful API for managing delays at runtime. All changes are automatically persisted to `application.yaml`.

#### Get All Endpoint Delays

```bash
# Get all configured endpoint delays
curl "http://localhost:8080/api/delays"

# Response example:
{
  "/clients/srvgetclientlist": 250,
  "/tasks/getByFilter": 150,
  "/cti/getCommunications": 300
}
```

#### Configure Delay for Specific Endpoint

```bash
# Set delay for specific endpoint
curl -X POST "http://localhost:8080/api/delays?endpoint=/clients/srvgetclientlist&delay=250"

# Response: "Successfully set delay for endpoint '/clients/srvgetclientlist' to 250 ms"
```

#### Get Delay for Specific Endpoint

```bash
# Method 1: Using query parameter
curl "http://localhost:8080/api/delays/endpoint?endpoint=/clients/srvgetclientlist"

# Method 2: Using path parameter (URL encoded)
curl "http://localhost:8080/api/delays/%2Fclients%2Fsrvgetclientlist"

# Response: 250
```

#### Update Delay for Existing Endpoint

```bash
# Update delay for specific endpoint
curl -X PUT "http://localhost:8080/api/delays?endpoint=/clients/srvgetclientlist&delay=300"

# Response: "Successfully updated delay for endpoint '/clients/srvgetclientlist' to 300 ms"
```

#### Remove Delay Configuration

```bash
# Remove delay configuration for endpoint
curl -X DELETE "http://localhost:8080/api/delays?endpoint=/clients/srvgetclientlist"

# Response: "Successfully removed delay configuration for endpoint: /clients/srvgetclientlist"
```

#### Global Delay Management

```bash
# Get current global delay
curl "http://localhost:8080/api/delays/global"

# Set global delay
curl -X POST "http://localhost:8080/api/delays/global?delay=200"

# Response: "Successfully set global delay to 200 ms"
```

### Legacy API Configuration (ConfigurationController)

The original configuration endpoints are still available for backward compatibility:

```bash
# Get current global delay
curl "http://localhost:8080/getDelta"

# Set global delay to 500ms
curl "http://localhost:8080/setDelta/500"

# Set delay for specific endpoint
curl -X POST "http://localhost:8080/setDeltaForEndpoint?endpoint=/clients/srvgetclientlist&delay=250"

# Get delay for specific endpoint
curl "http://localhost:8080/getDelayForEndpoint?endpoint=/clients/srvgetclientlist"

# Remove delay configuration for endpoint
curl -X DELETE "http://localhost:8080/removeDelayForEndpoint?endpoint=/clients/srvgetclientlist"

# Get all configured endpoint delays
curl "http://localhost:8080/getAllDelays"
```

### Delay Priority and Behavior

Delays are applied in the following priority order:
1. **Runtime configured delays** (set via DelayController API)
2. **Configuration file delays** (from `application.yaml`)
3. **Global delay** (if no endpoint-specific delay is configured)
4. **Default delay** (fallback value: 100ms)

### Automatic Persistence

All delay changes made through the API are automatically persisted to the `application.yaml` file, ensuring that configuration survives application restarts. The application uses SnakeYAML to maintain proper YAML formatting and structure.

### Example Workflow

```bash
# 1. Check current delays
curl "http://localhost:8080/api/delays"

# 2. Set delays for different endpoints
curl -X POST "http://localhost:8080/api/delays?endpoint=/clients/srvgetclientlist&delay=200"
curl -X POST "http://localhost:8080/api/delays?endpoint=/tasks/getByFilter&delay=150"
curl -X POST "http://localhost:8080/api/delays?endpoint=/cti/getCommunications&delay=300"

# 3. Set global delay for unconfigured endpoints
curl -X POST "http://localhost:8080/api/delays/global?delay=100"

# 4. Test endpoint with delay
time curl -X POST "http://localhost:8080/clients/srvgetclientlist" -H "Content-Type: application/json" -d '{}'

# 5. View all configured delays
curl "http://localhost:8080/api/delays"

# 6. Remove specific delay
curl -X DELETE "http://localhost:8080/api/delays?endpoint=/tasks/getByFilter"
```

## Endpoints Overview

### Clients Controller
- `POST /clients/srvgetclientlist` - Main clients operations
- `POST /clients/srvgetclientlist/clients/searchByLastName` - Search by last name
- `POST /clients/pprbBhepService` - PPRB BHEP service operations
- `POST /clients/teams/get` - Get teams
- `GET /sbpemployeeinfo/v1/employee` - Employee information
- And many more...

### Tasks Controller  
- `POST /tasks/getByFilter` - Get tasks by filter
- `POST /tasks/getTaskById` - Get task by ID
- `POST /tasks/offers` - Get offers
- `POST /tasks/marking/getById` - Get marking by ID
- `GET /tasks/sbpemployeeinfo/v1/employee` - Employee information for tasks
- And many more...

### CTI Controller
- `GET /cti/getCommunications` - Get communications history
- `GET /cti/getClientPhones` - Get client phone numbers
- `POST /cti/call/init` - Initialize call session
- `GET /employees/{fullEmployeeNumber}/phones` - Employee address book
- `GET /cti/sbpemployeeinfo/v1/employee` - Employee information for CTI
- And many more...

### Client Card Controller
- `POST /clients/getClientCardFromCRMandEPK` - Main client card operations
- `POST /clientcard/positions/get` - Get client positions
- `POST /clients/getClientCardFromCRMandEPK/rest/v1/context` - SBP context
- `POST /services/create-structure` - Create ECM structure
- `GET /clientcard/sbpemployeeinfo/v1/employee` - Employee information for client card
- And many more...

## Logging

Centralized logging with structured output:
- Console logging for development
- File logging with rotation
- Configurable levels per component
- JSON-structured logging support for production

Log files are written to `logs/armsb-mock.log` with automatic rotation.

## Migration Notes

All endpoints have been migrated from the original services while preserving:
- Original request/response formats
- Business logic and conditional responses  
- Delay mechanisms
- Error handling patterns

Endpoint conflicts were resolved by adding service-specific prefixes where necessary:
- `/cti/sbpemployeeinfo/v1/employee` for CTI employee endpoint
- `/tasks/sbpemployeeinfo/v1/employee` for Tasks employee endpoint  
- `/clientcard/sbpemployeeinfo/v1/employee` for Client Card employee endpoint
- And similar patterns for other conflicting endpoints

## Development

### Project Structure
```
src/main/java/sb1/stub/armsbmock/
├── ArmsbMockApplication.java          # Main application class
├── config/
│   └── ArmsbMockConfig.java          # Configuration properties
├── controller/
│   ├── ClientsController.java        # Clients endpoints
│   ├── TasksController.java          # Tasks endpoints  
│   ├── CtiController.java            # CTI endpoints
│   ├── ClientCardController.java     # Client Card endpoints
│   └── ConfigurationController.java  # Configuration endpoints
├── data/
│   ├── ClientsJsonData.java          # Clients response data
│   ├── TasksJsonData.java            # Tasks response data
│   ├── CtiJsonData.java              # CTI response data
│   └── ClientCardJsonData.java       # Client Card response data
└── service/
    └── DelayService.java             # Delay management service
```

### Building from Source
```bash
# Clone the repository
git clone <repository-url>
cd armsb-mock

# Build with Maven
mvn clean compile package

# Run tests  
mvn test

# Run the application
mvn spring-boot:run
```

## License

This project is part of the ARMSB mock services suite.