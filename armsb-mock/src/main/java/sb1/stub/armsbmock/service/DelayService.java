package sb1.stub.armsbmock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sb1.stub.armsbmock.config.ArmsbMockConfig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Slf4j
@Service
public class DelayService {
    
    private static long globalDelta = 100L;
    
    /**
     * Runtime endpoint-specific delays that can be set dynamically
     */
    private final Map<String, Long> runtimeDelays = new ConcurrentHashMap<>();
    
    @Autowired
    private ArmsbMockConfig config;
    
    /**
     * Apply delay without specifying endpoint (legacy method)
     */
    public void applyDelay() {
        applyDelay(null);
    }
    
    /**
     * Apply delay for specific endpoint
     * @param endpoint the endpoint URI to check for specific delay
     */
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
    
    /**
     * Get delay time for specific endpoint
     * Priority order: runtime delays > config delays > global delta > default delay
     */
    private long getDelayForEndpoint(String endpoint) {
        if (endpoint != null) {
            // Check runtime delays first (set via API)
            Long runtimeDelay = runtimeDelays.get(endpoint);
            if (runtimeDelay != null) {
                log.debug("Using runtime delay for endpoint '{}': {} ms", endpoint, runtimeDelay);
                return runtimeDelay;
            }
            
            // Check configuration delays
            Long configDelay = config.getDelays().get(endpoint);
            if (configDelay != null) {
                log.debug("Using config delay for endpoint '{}': {} ms", endpoint, configDelay);
                return configDelay;
            }
        }
        
        // Fall back to global delta or default delay
        long defaultDelayTime = globalDelta > 0 ? globalDelta : config.getDefaultDelay();
        log.debug("Using default delay for endpoint '{}': {} ms", endpoint, defaultDelayTime);
        return defaultDelayTime;
    }
    
    /**
     * Set delay for specific endpoint at runtime
     */
    public void setDelayForEndpoint(String endpoint, long delay) {
        log.info("Setting runtime delay for endpoint '{}' to: {} ms", endpoint, delay);
        runtimeDelays.put(endpoint, delay);
    }
    
    /**
     * Remove runtime delay for specific endpoint
     */
    public void removeDelayForEndpoint(String endpoint) {
        log.info("Removing runtime delay for endpoint '{}'", endpoint);
        runtimeDelays.remove(endpoint);
    }
    
    /**
     * Get current delay for specific endpoint
     */
    public long getDelayForEndpoint(String endpoint, boolean includeDefaults) {
        if (endpoint != null) {
            // Check runtime delays first
            Long runtimeDelay = runtimeDelays.get(endpoint);
            if (runtimeDelay != null) {
                return runtimeDelay;
            }
            
            // Check configuration delays
            Long configDelay = config.getDelays().get(endpoint);
            if (configDelay != null) {
                return configDelay;
            }
        }
        
        if (includeDefaults) {
            return globalDelta > 0 ? globalDelta : config.getDefaultDelay();
        }
        
        return -1; // No specific delay configured
    }
    
    /**
     * Get all runtime delays
     */
    public Map<String, Long> getRuntimeDelays() {
        return new ConcurrentHashMap<>(runtimeDelays);
    }
    
    public void setGlobalDelta(long delta) {
        log.info("Setting global delta to: {} ms", delta);
        globalDelta = delta;
    }
    
    public long getGlobalDelta() {
        return globalDelta;
    }
}