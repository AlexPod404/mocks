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

Configure the application using environment variables or `application.properties`:

```properties
# Server configuration
server.port=8080

# ARMSB Mock Configuration
armsb.mock.default-delay=300

# Module enablement
armsb.mock.clients.enabled=true
armsb.mock.tasks.enabled=true
armsb.mock.cti.enabled=true
armsb.mock.client-card.enabled=true

# Individual endpoint delay configuration
# All endpoints default to armsb.mock.default-delay value
armsb.mock.delays./clients/srvgetclientlist=250
armsb.mock.delays./cti/getCommunications=400
armsb.mock.delays./tasks/getByFilter=150

# Logging
logging.level.root=INFO
logging.level.sb1.stub.armsbmock=DEBUG
```

### Environment Variables

- `SERVER_PORT` - Server port (default: 8080)
- `ARMSB_DEFAULT_DELAY` - Default response delay in ms (default: 300)
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

### Static Configuration (application.properties)

Configure default delays and specific endpoint delays in the configuration file:

```properties
# ARMSB Mock Configuration
armsb.mock.default-delay=300

# Individual endpoint delay configuration
# All endpoints automatically default to armsb.mock.default-delay value
armsb.mock.delays./clients/srvgetclientlist=250
armsb.mock.delays./cti/getCommunications=400
armsb.mock.delays./tasks/getByFilter=150
armsb.mock.delays./templates/get=200

# You can configure any endpoint by adding:
# armsb.mock.delays.<endpoint-path>=<delay-in-milliseconds>
```

#### Environment Variable Support

You can also configure individual endpoint delays using environment variables:

```bash
# Set default delay
export ARMSB_DEFAULT_DELAY=300

# Set specific endpoint delays
export ARMSB_DELAY_CLIENTS_SRVGET_CLIENT_LIST=250
export ARMSB_DELAY_CTI_GET_COMMUNICATIONS=400
export ARMSB_DELAY_TASKS_GET_BY_FILTER=150
```

All endpoint delays support environment variable overrides. The naming convention is:
- `ARMSB_DELAY_` prefix
- Endpoint path converted to uppercase
- Special characters replaced with underscores
- Example: `/clients/srvgetclientlist` → `ARMSB_DELAY_CLIENTS_SRVGET_CLIENT_LIST`

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

The DelayService automatically initializes default delays for all armsb-mock endpoints during application startup using a @PostConstruct method. This ensures that every endpoint has a configured delay even if not explicitly set in the configuration file.

**Complete Endpoint Coverage**: The system now automatically configures delays for all 50+ endpoints in the application, including:
- CTI endpoints (communications, phone services, employee info)
- Client endpoints (client lists, search, teams)
- Client Card endpoints (positions, employee data)
- Task endpoints (filters, offers, marking)
- Template and values endpoints
- General service endpoints

All endpoints are initialized with the default delay value (300ms) unless explicitly configured.

Delays are applied in the following priority order:
1. **Runtime configured delays** (set via DelayController API)
2. **Configuration file delays** (from `application.yaml`)
3. **Initialized default delays** (set during startup via @PostConstruct)
4. **Global delay** (if no endpoint-specific delay is configured)
5. **Default delay** (fallback value: 300ms)

### Automatic Persistence

All delay changes made through the API are automatically persisted to the `application.properties` file, ensuring that configuration survives application restarts. The application uses standard Java Properties format to maintain proper formatting and structure.

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