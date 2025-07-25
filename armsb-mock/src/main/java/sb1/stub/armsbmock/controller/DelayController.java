package sb1.stub.armsbmock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sb1.stub.armsbmock.service.DelayService;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/delays")
@Tag(name = "Delays", description = "Delay management API for mock endpoints")
public class DelayController {

    @Autowired
    private DelayService delayService;

    @Operation(summary = "Get all endpoint delays", description = "Retrieve all configured endpoint delays")
    @GetMapping
    public ResponseEntity<Map<String, Long>> getAllDelays() {
        log.info("DelayController.getAllDelays called");
        Map<String, Long> delays = delayService.getAllEndpointDelays();
        log.debug("Returning {} endpoint delay configurations", delays.size());
        return ResponseEntity.ok(delays);
    }

    @Operation(summary = "Get delay for specific endpoint", description = "Get delay configuration for a specific endpoint")
    @GetMapping("/{endpoint}")
    public ResponseEntity<Long> getDelayForEndpointByPath(
            @Parameter(description = "Endpoint URI") @PathVariable String endpoint) {
        log.info("DelayController.getDelayForEndpointByPath called for endpoint: {}", endpoint);
        
        // URL decode the endpoint parameter
        endpoint = java.net.URLDecoder.decode(endpoint, java.nio.charset.StandardCharsets.UTF_8);
        
        Long delay = delayService.getDelayForEndpoint(endpoint, true);
        log.info("Delay for endpoint '{}': {} ms", endpoint, delay);
        return ResponseEntity.ok(delay);
    }

    @Operation(summary = "Set delay for specific endpoint", description = "Configure delay for a specific endpoint")
    @PostMapping
    public ResponseEntity<String> setDelayForEndpoint(
            @Parameter(description = "Endpoint URI") @RequestParam String endpoint,
            @Parameter(description = "Delay value in milliseconds") @RequestParam long delay) {
        log.info("DelayController.setDelayForEndpoint called for endpoint '{}' with delay: {} ms", endpoint, delay);
        
        delayService.setDelayForEndpoint(endpoint, delay);
        String response = String.format("Successfully set delay for endpoint '%s' to %d ms", endpoint, delay);
        log.info(response);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get delay for specific endpoint", description = "Get delay configuration for a specific endpoint")
    @GetMapping("/endpoint")
    public ResponseEntity<Long> getDelayForEndpoint(
            @Parameter(description = "Endpoint URI") @RequestParam String endpoint) {
        log.info("DelayController.getDelayForEndpoint called for endpoint: {}", endpoint);
        
        Long delay = delayService.getDelayForEndpoint(endpoint, true);
        log.info("Delay for endpoint '{}': {} ms", endpoint, delay);
        return ResponseEntity.ok(delay);
    }

    @Operation(summary = "Update delay for specific endpoint", description = "Update delay configuration for a specific endpoint")
    @PutMapping
    public ResponseEntity<String> updateDelayForEndpoint(
            @Parameter(description = "Endpoint URI") @RequestParam String endpoint,
            @Parameter(description = "Delay value in milliseconds") @RequestParam long delay) {
        log.info("DelayController.updateDelayForEndpoint called for endpoint '{}' with delay: {} ms", endpoint, delay);
        
        delayService.setDelayForEndpoint(endpoint, delay);
        String response = String.format("Successfully updated delay for endpoint '%s' to %d ms", endpoint, delay);
        log.info(response);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Remove delay for specific endpoint", description = "Remove delay configuration for a specific endpoint")
    @DeleteMapping
    public ResponseEntity<String> removeDelayForEndpoint(
            @Parameter(description = "Endpoint URI") @RequestParam String endpoint) {
        log.info("DelayController.removeDelayForEndpoint called for endpoint: {}", endpoint);
        
        delayService.removeDelayForEndpoint(endpoint);
        String response = String.format("Successfully removed delay configuration for endpoint: %s", endpoint);
        log.info(response);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Set global delay", description = "Configure global delay for all mock responses")
    @PostMapping("/global")
    public ResponseEntity<String> setGlobalDelay(
            @Parameter(description = "Global delay value in milliseconds") @RequestParam long delay) {
        log.info("DelayController.setGlobalDelay called with delay: {} ms", delay);
        delayService.setGlobalDelta(delay);
        String response = String.format("Successfully set global delay to %d ms", delay);
        log.info(response);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get global delay", description = "Get current global delay value")
    @GetMapping("/global")
    public ResponseEntity<Long> getGlobalDelay() {
        long currentDelay = delayService.getGlobalDelta();
        log.info("DelayController.getGlobalDelay called, returning: {} ms", currentDelay);
        return ResponseEntity.ok(currentDelay);
    }

}