package sb1.stub.armsbmock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sb1.stub.armsbmock.service.DelayService;

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

}