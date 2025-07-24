package sb1.stub.armsbmock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;
import sb1.stub.armsbmock.data.CtiJsonData;
import sb1.stub.armsbmock.service.DelayService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping
@Tag(name = "CTI", description = "ARMSB CTI (Computer Telephony Integration) subsystem endpoints")
@ConditionalOnProperty(prefix = "armsb.mock.cti", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CtiController {

    @Autowired
    private DelayService delayService;

    @Operation(summary = "Get communications", description = "Get communication history and records")
    @RequestMapping(value = "/cti/getCommunications", method = RequestMethod.GET, produces = "application/json")
    public String getCommunications(HttpServletRequest request) {
        log.info("CtiController.getCommunications called");
        delayService.applyDelay(request.getRequestURI());
        String response = CtiJsonData.getCommunications();
        log.info("CtiController.getCommunications completed");
        return response;
    }

    @Operation(summary = "Get client phone numbers", description = "Get phone numbers associated with clients")
    @RequestMapping(value = "/cti/getClientPhones", method = RequestMethod.GET, produces = "application/json")
    public String getClientPhones(HttpServletRequest request) {
        log.info("CtiController.getClientPhones called");
        delayService.applyDelay(request.getRequestURI());
        String response = CtiJsonData.getClientPhones();
        log.info("CtiController.getClientPhones completed");
        return response;
    }

    @Operation(summary = "Initialize call", description = "Initialize a new call session")
    @RequestMapping(value = "/cti/call/init", method = RequestMethod.POST, produces = "application/json")
    public String initCall(HttpServletRequest request) {
        log.info("CtiController.initCall called");
        delayService.applyDelay(request.getRequestURI());
        String response = CtiJsonData.initCall();
        log.info("CtiController.initCall completed");
        return response;
    }

    @Operation(summary = "Get employee address book", description = "Get phone numbers for employee")
    @RequestMapping(value = "/employees/{fullEmployeeNumber}/phones", method = RequestMethod.GET, produces = "application/json")
    public String getEmployeeAddressBook(@Parameter(description = "Full employee number") @PathVariable String fullEmployeeNumber, HttpServletRequest request) {
        log.info("CtiController.getEmployeeAddressBook called for employee: {}", fullEmployeeNumber);
        delayService.applyDelay(request.getRequestURI());
        String response = CtiJsonData.addressBook();
        log.info("CtiController.getEmployeeAddressBook completed");
        return response;
    }

    @Operation(summary = "Get employee information for CTI")
    @RequestMapping(value = "/cti/sbpemployeeinfo/v1/employee", method = RequestMethod.GET, produces = "application/json")
    public String getEmployee(HttpServletRequest request) {
        log.info("CtiController.getEmployee called");
        delayService.applyDelay(request.getRequestURI());
        String response = CtiJsonData.employee();
        log.info("CtiController.getEmployee completed");
        return response;
    }

    @Operation(summary = "Get positions", description = "Get available CTI positions")
    @RequestMapping(value = "/cti/positions/get", method = RequestMethod.GET, produces = "application/json")
    public String getPositions(HttpServletRequest request) {
        log.info("CtiController.getPositions called");
        delayService.applyDelay(request.getRequestURI());
        String response = CtiJsonData.positionsGet();
        log.info("CtiController.getPositions completed");
        return response;
    }

    @Operation(summary = "Set notification", description = "Set call notification or status")
    @RequestMapping(value = "/cti/setNotification", method = RequestMethod.POST, produces = "application/json")
    public String setNotification(HttpServletRequest request) {
        log.info("CtiController.setNotification called");
        delayService.applyDelay(request.getRequestURI());
        String response = CtiJsonData.setNotification();
        log.info("CtiController.setNotification completed");
        return response;
    }

    @Operation(summary = "PPRB notification", description = "Handle PPRB notification operations")
    @RequestMapping(value = "/pprbNotification", method = RequestMethod.POST, produces = "application/json")
    public String pprbNotification(HttpServletRequest request) {
        log.info("CtiController.pprbNotification called");
        delayService.applyDelay(request.getRequestURI());
        String response = CtiJsonData.pprbNotification();
        log.info("CtiController.pprbNotification completed");
        return response;
    }

}