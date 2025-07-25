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
@RequestMapping
@Tag(name = "Configuration", description = "System configuration endpoints")
public class ConfigurationController {

    @Autowired
    private DelayService delayService;

    @Operation(summary = "Set delay in milliseconds", description = "Configure global delay for all mock responses")
    @RequestMapping(value = "/setDelta/{delta}", method = RequestMethod.GET)
    @ResponseBody
    public String setDelta(@Parameter(description = "Delay value in milliseconds") @PathVariable String delta) {
        log.info("Setting delta to: {}", delta);
        delayService.setGlobalDelta(Long.parseLong(delta, 10));
        String response = "Set delta to: " + delayService.getGlobalDelta() + "\n";
        log.info("Delta set successfully: {}", delayService.getGlobalDelta());
        return response;
    }

    @Operation(summary = "Get current delay", description = "Get current global delay value")
    @RequestMapping(value = "/getDelta", method = RequestMethod.GET)
    @ResponseBody
    public String getDelta() {
        long currentDelta = delayService.getGlobalDelta();
        log.info("Current delta requested: {}", currentDelta);
        return "Delta is: " + currentDelta + "\n";
    }

    @Operation(summary = "Set delay for specific endpoint", description = "Configure delay for a specific endpoint")
    @RequestMapping(value = "/setDeltaForEndpoint", method = RequestMethod.POST)
    @ResponseBody
    public String setDeltaForEndpoint(
            @Parameter(description = "Endpoint URI") @RequestParam String endpoint,
            @Parameter(description = "Delay value in milliseconds") @RequestParam long delay) {
        log.info("Setting delay for endpoint '{}' to: {} ms", endpoint, delay);
        delayService.setDelayForEndpoint(endpoint, delay);
        String response = String.format("Set delay for endpoint '%s' to: %d ms\n", endpoint, delay);
        log.info("Endpoint delay set successfully for '{}': {} ms", endpoint, delay);
        return response;
    }

    @Operation(summary = "Get delay for specific endpoint", description = "Get delay configuration for a specific endpoint")
    @RequestMapping(value = "/getDelayForEndpoint", method = RequestMethod.GET)
    @ResponseBody
    public String getDelayForEndpoint(@Parameter(description = "Endpoint URI") @RequestParam String endpoint) {
        Long delay = delayService.getDelayForEndpoint(endpoint, false);
        log.info("Delay requested for endpoint '{}': {}", endpoint, delay);
        if (delay != null) {
            return String.format("Delay for endpoint '%s' is: %d ms\n", endpoint, delay);
        } else {
            return String.format("No specific delay configured for endpoint '%s', using default\n", endpoint);
        }
    }

    @Operation(summary = "Remove delay for specific endpoint", description = "Remove delay configuration for a specific endpoint")
    @RequestMapping(value = "/removeDelayForEndpoint", method = RequestMethod.DELETE)
    @ResponseBody
    public String removeDelayForEndpoint(@Parameter(description = "Endpoint URI") @RequestParam String endpoint) {
        log.info("Removing delay configuration for endpoint: {}", endpoint);
        delayService.removeDelayForEndpoint(endpoint);
        String response = String.format("Removed delay configuration for endpoint: %s\n", endpoint);
        log.info("Endpoint delay configuration removed for: {}", endpoint);
        return response;
    }

    @Operation(summary = "Get all endpoint delays", description = "Get all configured endpoint delays")
    @RequestMapping(value = "/getAllDelays", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Long> getAllDelays() {
        log.info("All endpoint delays requested");
        Map<String, Long> delays = delayService.getAllEndpointDelays();
        log.debug("Returning {} endpoint delay configurations", delays.size());
        return delays;
    }

}