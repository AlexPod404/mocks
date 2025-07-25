package sb1.stub.armsbmock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions;
import sb1.stub.armsbmock.config.ArmsbMockConfig;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
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
    
    private final String configFile = "src/main/resources/application.yaml";
    
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
        updateApplicationYaml(endpoint, delay);
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
        removeDelayFromApplicationYaml(endpoint);
    }
    
    private void updateApplicationYaml(String endpoint, long delay) {
        try {
            Yaml yaml = createYaml();
            Map<String, Object> data = loadYamlFile(yaml);
            
            // Navigate to armsb.mock.delays section
            Map<String, Object> armsb = (Map<String, Object>) data.computeIfAbsent("armsb", k -> new LinkedHashMap<>());
            Map<String, Object> mock = (Map<String, Object>) armsb.computeIfAbsent("mock", k -> new LinkedHashMap<>());
            Map<String, Object> delays = (Map<String, Object>) mock.computeIfAbsent("delays", k -> new LinkedHashMap<>());
            
            // Update the delay
            delays.put(endpoint, delay);
            
            // Write back to file
            writeYamlFile(yaml, data);
            
            log.info("Successfully updated application.yaml with delay for endpoint '{}': {} ms", endpoint, delay);
        } catch (Exception e) {
            log.error("Failed to update application.yaml with delay for endpoint '{}': {}", endpoint, e.getMessage(), e);
        }
    }
    
    private void removeDelayFromApplicationYaml(String endpoint) {
        try {
            Yaml yaml = createYaml();
            Map<String, Object> data = loadYamlFile(yaml);
            
            // Navigate to armsb.mock.delays section
            Map<String, Object> armsb = (Map<String, Object>) data.get("armsb");
            if (armsb != null) {
                Map<String, Object> mock = (Map<String, Object>) armsb.get("mock");
                if (mock != null) {
                    Map<String, Object> delays = (Map<String, Object>) mock.get("delays");
                    if (delays != null) {
                        delays.remove(endpoint);
                        
                        // Write back to file
                        writeYamlFile(yaml, data);
                        
                        log.info("Successfully removed delay from application.yaml for endpoint: {}", endpoint);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to remove delay from application.yaml for endpoint '{}': {}", endpoint, e.getMessage(), e);
        }
    }
    
    private Yaml createYaml() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        options.setIndent(2);
        return new Yaml(options);
    }
    
    @SuppressWarnings("unchecked")
    private Map<String, Object> loadYamlFile(Yaml yaml) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(configFile)) {
            Map<String, Object> data = yaml.load(inputStream);
            return data != null ? data : new LinkedHashMap<>();
        }
    }
    
    private void writeYamlFile(Yaml yaml, Map<String, Object> data) throws IOException {
        try (FileWriter writer = new FileWriter(configFile)) {
            yaml.dump(data, writer);
        }
    }
}