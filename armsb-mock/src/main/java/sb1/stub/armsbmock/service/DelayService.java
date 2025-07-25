package sb1.stub.armsbmock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sb1.stub.armsbmock.config.ArmsbMockConfig;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class DelayService {
    
    private static long globalDelta = 100L;
    private final Map<String, Long> endpointDelays = new ConcurrentHashMap<>();
    
    @Autowired
    private ArmsbMockConfig config;
    
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
    }
    
    @PostConstruct
    public void initializeEndpointDelays() {
        log.info("Initializing individual delays for armsb-mock endpoints");
        
        // Set 300ms delay for all armsb-mock endpoints
        setDelayForEndpoint("/cti/getCommunications", 300L);
        setDelayForEndpoint("/cti/getClientPhones", 300L);
        setDelayForEndpoint("/cti/call/init", 300L);
        setDelayForEndpoint("/employees/{fullEmployeeNumber}/phones", 300L);
        setDelayForEndpoint("/cti/sbpemployeeinfo/v1/employee", 300L);
        setDelayForEndpoint("/clientcard/sbpemployeeinfo/v1/employee", 300L);
        setDelayForEndpoint("/clientcard/positions/get", 300L);
        setDelayForEndpoint("/clients/getClientCardFromCRMandEPK/rest/v1/context", 300L);
        setDelayForEndpoint("/clientcard/employee/com.sbt.bpspe.core.json.rpc.api.Employee", 300L);
        setDelayForEndpoint("/clients/srvgetclientlist", 300L);
        setDelayForEndpoint("/clients/pprbBhepService", 300L);
        setDelayForEndpoint("/clients/teams/get", 300L);
        setDelayForEndpoint("/clients/pprbClients", 300L);
        setDelayForEndpoint("/tasks/getByFilter", 300L);
        setDelayForEndpoint("/tasks/getTaskById", 300L);
        setDelayForEndpoint("/tasks/offers", 300L);
        setDelayForEndpoint("/tasks/marking/getById", 300L);
        setDelayForEndpoint("/tasks/sbpemployeeinfo/v1/employee", 300L);
        setDelayForEndpoint("/templates/get", 300L);
        setDelayForEndpoint("/templates/getFilters", 300L);
        setDelayForEndpoint("/templates/update", 300L);
        setDelayForEndpoint("/setDelta/{delta}", 300L);
        setDelayForEndpoint("/getDelta", 300L);
        setDelayForEndpoint("/setDeltaForEndpoint", 300L);
        setDelayForEndpoint("/getDelayForEndpoint", 300L);
        setDelayForEndpoint("/removeDelayForEndpoint", 300L);
        
        log.info("Successfully initialized delays for {} armsb-mock endpoints", 25);
    }
}