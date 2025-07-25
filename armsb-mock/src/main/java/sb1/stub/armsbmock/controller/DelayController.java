package sb1.stub.armsbmock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sb1.stub.armsbmock.service.DelayService;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/delays")
@Tag(name = "Delays", description = "Delay management endpoints")
public class DelayController {

    @Autowired
    private DelayService delayService;

    @Operation(summary = "Get all delays", description = "Get all configured endpoint delays")
    @GetMapping
    public Map<String, Long> getAllDelays() {
        log.info("GET /delays - retrieving all delays");
        Map<String, Long> delays = delayService.getAllEndpointDelays();
        log.debug("Returning {} endpoint delay configurations", delays.size());
        return delays;
    }

    @Operation(summary = "Get delay for specific endpoint", description = "Get delay configuration for a specific endpoint")
    @GetMapping("/endpoint")
    public Map<String, Object> getDelayForEndpoint(@Parameter(description = "Endpoint URI") @RequestParam String endpoint) {
        log.info("GET /delays/endpoint?endpoint={} - retrieving delay for endpoint", endpoint);
        
        // Add leading slash if not present
        if (!endpoint.startsWith("/")) {
            endpoint = "/" + endpoint;
        }
        
        Long delay = delayService.getDelayForEndpoint(endpoint, true);
        Map<String, Object> response = Map.of(
            "endpoint", endpoint,
            "delay", delay
        );
        
        log.debug("Delay for endpoint '{}': {} ms", endpoint, delay);
        return response;
    }

    @Operation(summary = "Set delay for endpoint", description = "Set delay configuration for a specific endpoint")
    @PostMapping("/set")
    public Map<String, Object> setDelay(
            @Parameter(description = "Endpoint URI") @RequestParam String endpoint,
            @Parameter(description = "Delay value in milliseconds") @RequestParam long delay) {
        log.info("POST /delays/set - setting delay for endpoint '{}' to {} ms", endpoint, delay);
        
        // Add leading slash if not present
        if (!endpoint.startsWith("/")) {
            endpoint = "/" + endpoint;
        }
        
        delayService.setDelayForEndpoint(endpoint, delay);
        
        Map<String, Object> response = Map.of(
            "endpoint", endpoint,
            "delay", delay,
            "status", "success",
            "message", String.format("Delay for endpoint '%s' set to %d ms", endpoint, delay)
        );
        
        log.info("Successfully set delay for endpoint '{}' to {} ms", endpoint, delay);
        return response;
    }
}