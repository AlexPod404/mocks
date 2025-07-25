package sb1.stub.armsbmock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sb1.stub.armsbmock.config.ArmsbMockConfig;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class DelayService {
    
    private static long globalDelta = 100L;
    private final Map<String, Long> endpointDelays = new ConcurrentHashMap<>();
    
    @Autowired
    private ArmsbMockConfig config;
    
    @Value("${spring.config.name:application}")
    private String configName;
    
    private final String configFile = "src/main/resources/application.properties";
    
    /**
     * List of all armsb-mock endpoints that should have default delays configured
     * This list is automatically generated from all controllers and includes all available endpoints
     */
    private static final List<String> ARMSB_ENDPOINTS = Arrays.asList(
        // CTI endpoints
        "/cti/getCommunications",
        "/cti/getClientPhones", 
        "/cti/call/init",
        "/cti/positions/get",
        "/cti/sbpemployeeinfo/v1/employee",
        "/cti/setNotification",
        "/employees/{fullEmployeeNumber}/phones",
        
        // Client endpoints
        "/clients/srvgetclientlist",
        "/clients/srvgetclientlist/clients/searchByLastName",
        "/clients/pprbBhepService",
        "/clients/pprbClients",
        "/clients/pprbClients/clients/getByTeamId",
        "/clients/teams/get",
        "/clients/ucpclients",
        "/clients/ucpclients/clients/get",
        "/clients/getClientCardFromCRMandEPK",
        "/clients/getClientCardFromCRMandEPK/rest/v1/context",
        "armsb/clients/v1/rest/getClientsForMassMailing",
        
        // Client Card endpoints  
        "/clientcard/positions/get",
        "/clientcard/sbpemployeeinfo/v1/employee",
        "/clientcard/teams/get",
        "/clientcard/employee/com.sbt.bpspe.core.json.rpc.api.Employee",
        
        // Task endpoints
        "/tasks",
        "/tasks/get",
        "/tasks/getByFilter",
        "/tasks/getFilters",
        "/tasks/getTaskById",
        "/tasks/offers",
        "/tasks/marking/check",
        "/tasks/marking/getByClient",
        "/tasks/marking/getById",
        "/tasks/sbpemployeeinfo/v1/employee",
        "/tasks/positions/get",
        "/tasks/teams/get",
        "/tasks/teams/free",
        "/tasks/clients/get",
        "/tasks/clients/getByTeamId",
        "/tasks/data-dictionary-service/rest/pm/ver.4.0/getRows",
        
        // Template endpoints
        "/templates/get",
        "/templates/getFilters",
        "/templates/update",
        
        // Values endpoints
        "/values/get",
        "/values/update",
        
        // General endpoints
        "/sbpemployeeinfo/v1/employee",
        "/employee/com.sbt.bpspe.core.json.rpc.api.Employee",
        "/services/create-structure",
        "/send",
        "/getCompanies",
        "/getCountersByClients",
        "/getGeneralCounters",
        "/getInfoByCompanyId",
        "/pprbNotification",
        "/sbolpro/netscanbh/v1/file/setRegionConfig",
        
        // Legacy configuration endpoints (maintain for backward compatibility)
        "/setDelta/{delta}",
        "/getDelta",
        "/setDeltaForEndpoint",
        "/getDelayForEndpoint",
        "/removeDelayForEndpoint",
        "/getAllDelays"
    );
    
    /**
     * Initialize default delays for all armsb-mock endpoints
     * This method runs after dependency injection is complete
     */
    @PostConstruct
    public void initializeEndpointDelays() {
        log.info("Initializing default delays for all armsb-mock endpoints");
        
        for (String endpoint : ARMSB_ENDPOINTS) {
            // Only initialize if endpoint doesn't already have a configured delay
            if (!config.getDelays().containsKey(endpoint) && !endpointDelays.containsKey(endpoint)) {
                endpointDelays.put(endpoint, config.getDefaultDelay());
                log.debug("Initialized default delay for endpoint '{}': {} ms", endpoint, config.getDefaultDelay());
            } else {
                log.debug("Endpoint '{}' already has configured delay, skipping initialization", endpoint);
            }
        }
        
        log.info("Completed initialization of default delays for {} endpoints", ARMSB_ENDPOINTS.size());
    }
    
    public void applyDelay() {
        try {
            long delayTime = globalDelta > 0 ? globalDelta : config.getDefaultDelay();
            log.debug("Applying global delay: {} ms", delayTime);
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Delay interrupted", e);
        }
    }
    
    public void applyDelay(String endpoint) {
        try {
            long delayTime = getDelayForEndpoint(endpoint);
            log.debug("Applying delay for endpoint '{}': {} ms", endpoint, delayTime);
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Delay interrupted for endpoint '{}'", endpoint, e);
        }
    }
    
    private long getDelayForEndpoint(String endpoint) {
        // First check runtime configured delays
        Long endpointDelay = endpointDelays.get(endpoint);
        if (endpointDelay != null) {
            log.debug("Using runtime delay for endpoint '{}': {} ms", endpoint, endpointDelay);
            return endpointDelay;
        }
        
        // Then check configuration delays
        endpointDelay = config.getDelays().get(endpoint);
        if (endpointDelay != null) {
            log.debug("Using configured delay for endpoint '{}': {} ms", endpoint, endpointDelay);
            return endpointDelay;
        }
        
        // Fall back to global delta or default delay
        long fallbackDelay = globalDelta > 0 ? globalDelta : config.getDefaultDelay();
        log.debug("Using fallback delay for endpoint '{}': {} ms", endpoint, fallbackDelay);
        return fallbackDelay;
    }
    
    public void setGlobalDelta(long delta) {
        log.info("Setting global delta to: {} ms", delta);
        globalDelta = delta;
    }
    
    public long getGlobalDelta() {
        return globalDelta;
    }
    
    public void setDelayForEndpoint(String endpoint, long delay) {
        log.info("Setting delay for endpoint '{}' to: {} ms", endpoint, delay);
        endpointDelays.put(endpoint, delay);
        updateApplicationProperties(endpoint, delay);
    }
    
    public Long getDelayForEndpoint(String endpoint, boolean includeDefaults) {
        if (includeDefaults) {
            return getDelayForEndpoint(endpoint);
        } else {
            // Only return explicitly set delays
            Long runtimeDelay = endpointDelays.get(endpoint);
            if (runtimeDelay != null) {
                return runtimeDelay;
            }
            return config.getDelays().get(endpoint);
        }
    }
    
    public Map<String, Long> getAllEndpointDelays() {
        Map<String, Long> allDelays = new ConcurrentHashMap<>(config.getDelays());
        allDelays.putAll(endpointDelays);
        return allDelays;
    }
    
    public void removeDelayForEndpoint(String endpoint) {
        log.info("Removing delay configuration for endpoint: {}", endpoint);
        endpointDelays.remove(endpoint);
        removeDelayFromApplicationProperties(endpoint);
    }
    
    private void updateApplicationProperties(String endpoint, long delay) {
        try {
            Properties props = loadPropertiesFile();
            
            // Create the property key for this endpoint
            String propertyKey = "armsb.mock.delays." + endpoint;
            
            // Update the delay
            props.setProperty(propertyKey, String.valueOf(delay));
            
            // Write back to file
            writePropertiesFile(props);
            
            log.info("Successfully updated application.properties with delay for endpoint '{}': {} ms", endpoint, delay);
        } catch (Exception e) {
            log.error("Failed to update application.properties with delay for endpoint '{}': {}", endpoint, e.getMessage(), e);
        }
    }
    
    private void removeDelayFromApplicationProperties(String endpoint) {
        try {
            Properties props = loadPropertiesFile();
            
            // Create the property key for this endpoint
            String propertyKey = "armsb.mock.delays." + endpoint;
            
            // Remove the property
            props.remove(propertyKey);
            
            // Write back to file
            writePropertiesFile(props);
            
            log.info("Successfully removed delay from application.properties for endpoint: {}", endpoint);
        } catch (Exception e) {
            log.error("Failed to remove delay from application.properties for endpoint '{}': {}", endpoint, e.getMessage(), e);
        }
    }
    
    private Properties loadPropertiesFile() throws IOException {
        Properties props = new Properties();
        try (FileInputStream inputStream = new FileInputStream(configFile)) {
            props.load(inputStream);
        }
        return props;
    }
    
    private void writePropertiesFile(Properties props) throws IOException {
        try (FileWriter writer = new FileWriter(configFile)) {
            props.store(writer, "ARMSB Mock Configuration - Updated by DelayService");
        }
    }
}