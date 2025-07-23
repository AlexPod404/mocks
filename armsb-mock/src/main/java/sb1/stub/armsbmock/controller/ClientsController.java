package sb1.stub.armsbmock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;
import sb1.stub.armsbmock.data.ClientsJsonData;
import sb1.stub.armsbmock.service.DelayService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping
@Tag(name = "Clients", description = "ARMSB Clients subsystem endpoints")
@ConditionalOnProperty(prefix = "armsb.mock.clients", name = "enabled", havingValue = "true", matchIfMissing = true)
public class ClientsController {

    @Autowired
    private DelayService delayService;

    @Operation(summary = "Get clients list", description = "Main clients endpoint handling various client operations")
    @ApiResponse(responseCode = "200", description = "Successful response", 
                content = @Content(examples = @ExampleObject(value = "{\"status\":\"success\"}")))
    @RequestMapping(value = "/clients/srvgetclientlist", method = RequestMethod.POST, produces = "application/json")
    public String getClientsList(@RequestBody String requestBody) {
        log.info("ClientsController.getClientsList called with request length: {}", requestBody.length());
        log.debug("Request body: {}", requestBody);
        
        String response;
        if (requestBody.contains("GetCompanionsByClientId")) {
            response = ClientsJsonData.getCompanions();
            log.debug("Returning companions data");
        } else if (requestBody.contains("GetEmployees")) {
            response = ClientsJsonData.getEmployees();
            log.debug("Returning employees data");
        } else if (requestBody.contains("{\"lastName\":\"Ivanov\",")) {
            response = ClientsJsonData.getVipClientsByFullName();
            log.debug("Returning VIP clients by full name");
        } else if (requestBody.contains("ARMSB_CLIENTS")) {
            response = ClientsJsonData.getAllClients();
            log.debug("Returning all clients");
        } else if ((requestBody.startsWith("{\"epkId\":")) && (requestBody.length() < 35)) {
            response = ClientsJsonData.getChangeSegmentService();
            log.debug("Returning change segment service response");
        } else if (requestBody.contains("\"dateFrom\": \"2024-04-10\"")) {
            response = ClientsJsonData.getPageByFilter();
            log.debug("Returning page by filter");
        } else if (requestBody.contains("\"massMailingId\": \"c6466dd4-f5af-40b3-8dfb-2146e8e3686b\"")) {
            response = ClientsJsonData.getMailingInfoPageByFilters();
            log.debug("Returning mailing info page by filters");
        } else if (requestBody.length() == 0) {
            response = ClientsJsonData.errorRequest();
            log.warn("Empty request body received");
        } else {
            response = ClientsJsonData.getAllClients();
            log.debug("Default response: all clients");
        }
        
        delayService.applyDelay();
        log.info("ClientsController.getClientsList completed");
        return response;
    }

    @Operation(summary = "Get clients for mass mailing")
    @RequestMapping(value = "armsb/clients/v1/rest/getClientsForMassMailing", method = RequestMethod.POST, produces = "application/json")
    public String getClientsForMassMailing(@RequestBody String requestBody) {
        log.info("ClientsController.getClientsForMassMailing called");
        log.debug("Request body: {}", requestBody);
        
        delayService.applyDelay();
        String response = ClientsJsonData.getMailingInfoPageByFilters();
        log.info("ClientsController.getClientsForMassMailing completed");
        return response;
    }

    @Operation(summary = "Get tasks by filter")
    @RequestMapping(value = "/tasks/get", method = RequestMethod.POST, produces = "application/json")
    public String getTasksByFilter(@RequestBody String requestBody) {
        log.info("ClientsController.getTasksByFilter called");
        log.debug("Request body: {}", requestBody);
        
        delayService.applyDelay();
        String response = ClientsJsonData.tasksGetByFilter();
        log.info("ClientsController.getTasksByFilter completed");
        return response;
    }

    @Operation(summary = "Search clients by last name")
    @RequestMapping(value = "/clients/srvgetclientlist/clients/searchByLastName", method = RequestMethod.POST, produces = "application/json")
    public String searchByLastName(@RequestBody String requestBody) {
        log.info("ClientsController.searchByLastName called");
        log.debug("Request body: {}", requestBody);
        
        delayService.applyDelay();
        String response = ClientsJsonData.getVipClientsByFullName();
        log.info("ClientsController.searchByLastName completed");
        return response;
    }

    @Operation(summary = "PPRB BHEP Service")
    @RequestMapping(value = "/clients/pprbBhepService", method = RequestMethod.POST, produces = "application/json")
    public String pprbBhepService(@RequestBody String requestBody) {
        log.info("ClientsController.pprbBhepService called");
        log.debug("Request body: {}", requestBody);
        
        String response;
        if (requestBody.contains("{\"method\":\"search\",\"id\":\"2\",")) {
            response = ClientsJsonData.pprbBhepService();
            log.debug("Returning PPRB BHEP service response");
        } else if (requestBody.length() == 0) {
            response = ClientsJsonData.errorRequest();
            log.warn("Empty request body received");
        } else {
            response = ClientsJsonData.pprbBhepService();
            log.debug("Default PPRB BHEP response");
        }
        
        delayService.applyDelay();
        log.info("ClientsController.pprbBhepService completed");
        return response;
    }

    @Operation(summary = "Get clients by team ID")
    @RequestMapping(value = "/clients/pprbClients/clients/getByTeamId", method = RequestMethod.POST, produces = "application/json")
    public String getByTeamId(HttpServletRequest request, HttpServletResponse response) {
        log.info("ClientsController.getByTeamId called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay();
            String responseData = ClientsJsonData.pprbClients();
            log.info("ClientsController.getByTeamId completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getByTeamId request", e);
            return ClientsJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get teams")
    @RequestMapping(value = "/clients/teams/get", method = RequestMethod.POST, produces = "application/json")
    public String getTeams(HttpServletRequest request, HttpServletResponse response) {
        log.info("ClientsController.getTeams called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay();
            String responseData = ClientsJsonData.getTeams();
            log.info("ClientsController.getTeams completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getTeams request", e);
            return ClientsJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get UCP clients")
    @RequestMapping(value = "/clients/ucpclients/clients/get", method = RequestMethod.POST, produces = "application/json")
    public String getUcpClients(HttpServletRequest request, HttpServletResponse response) {
        log.info("ClientsController.getUcpClients called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay();
            String responseData = ClientsJsonData.ucpClients();
            log.info("ClientsController.getUcpClients completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getUcpClients request", e);
            return ClientsJsonData.errorRequest();
        }
    }

    @Operation(summary = "Employee service for send operations")
    @RequestMapping(value = "/employee/com.sbt.bpspe.core.json.rpc.api.Employee", method = RequestMethod.POST, produces = "application/json")
    public String forSend(HttpServletRequest request, HttpServletResponse response) {
        log.info("ClientsController.forSend called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay();
            String responseData = ClientsJsonData.forSend();
            log.info("ClientsController.forSend completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing forSend request", e);
            return ClientsJsonData.errorRequest();
        }
    }

    @Operation(summary = "PPRB Clients general endpoint")
    @RequestMapping(value = "/clients/pprbClients", method = RequestMethod.POST, produces = "application/json")
    public String pprbClients(@RequestBody String requestBody) {
        log.info("ClientsController.pprbClients called");
        log.debug("Request body: {}", requestBody);
        
        String response;
        if (requestBody.contains("\"channel\":\"VIP\",\"kmCode\":[],\"id\":[],\"type\":\"DEF\",\"status\":[\"ACTIVE")) {
            response = ClientsJsonData.getTeams();
            log.debug("Returning teams data");
        } else if (requestBody.contains("{\"id\": [")) {
            response = ClientsJsonData.getClientsId();
            log.debug("Returning clients by ID");
        } else if (requestBody.contains("\",\"changedBy\":\"NOT_FOUND\",\"status\":\"INACTIVE\"")) {
            response = ClientsJsonData.getChangedBy();
            log.debug("Returning changed by data");
        } else if (requestBody.length() == 0) {
            response = ClientsJsonData.errorRequest();
            log.warn("Empty request body received");
        } else {
            response = ClientsJsonData.pprbClients();
            log.debug("Default PPRB clients response");
        }
        
        delayService.applyDelay();
        log.info("ClientsController.pprbClients completed");
        return response;
    }

    @Operation(summary = "UCP Clients endpoint")
    @RequestMapping(value = "/clients/ucpclients", method = RequestMethod.POST, produces = "application/json")
    public String ucpClients(@RequestBody String requestBody) {
        log.info("ClientsController.ucpClients called");
        log.debug("Request body: {}", requestBody);
        
        String response;
        if (requestBody.contains("ucpIds")) {
            response = ClientsJsonData.ucpClients();
            log.debug("Returning UCP clients data");
        } else if (requestBody.length() == 0) {
            response = ClientsJsonData.errorRequest();
            log.warn("Empty request body received");
        } else {
            response = ClientsJsonData.ucpClients();
            log.debug("Default UCP clients response");
        }
        
        delayService.applyDelay();
        log.info("ClientsController.ucpClients completed");
        return response;
    }

    @Operation(summary = "Get employee information")
    @RequestMapping(value = "/sbpemployeeinfo/v1/employee", method = RequestMethod.GET, produces = "application/json")
    public String getEmployee() {
        log.info("ClientsController.getEmployee called");
        delayService.applyDelay();
        String response = ClientsJsonData.employee();
        log.info("ClientsController.getEmployee completed");
        return response;
    }

}