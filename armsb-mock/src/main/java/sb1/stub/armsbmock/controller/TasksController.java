package sb1.stub.armsbmock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;
import sb1.stub.armsbmock.data.TasksJsonData;
import sb1.stub.armsbmock.service.DelayService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping
@Tag(name = "Tasks", description = "ARMSB Tasks subsystem endpoints")
@ConditionalOnProperty(prefix = "armsb.mock.tasks", name = "enabled", havingValue = "true", matchIfMissing = true)
public class TasksController {

    @Autowired
    private DelayService delayService;

    @Operation(summary = "Get employee information for tasks")
    @RequestMapping(value = "/tasks/sbpemployeeinfo/v1/employee", method = RequestMethod.GET, produces = "application/json")
    public String getEmployee(HttpServletRequest request) {
        log.info("TasksController.getEmployee called");
        delayService.applyDelay(request.getRequestURI());
        String response = TasksJsonData.employee();
        log.info("TasksController.getEmployee completed");
        return response;
    }

    @Operation(summary = "Get clients by team ID")
    @RequestMapping(value = "/tasks/clients/getByTeamId", method = RequestMethod.POST, produces = "application/json")
    public String getByTeamId(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.getByTeamId called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.getByTeamId();
            log.info("TasksController.getByTeamId completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getByTeamId request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get clients by client ID")
    @RequestMapping(value = "/tasks/clients/get", method = RequestMethod.POST, produces = "application/json")
    public String getByClientId(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.getByClientId called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.getByClientId();
            log.info("TasksController.getByClientId completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getByClientId request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get templates")
    @RequestMapping(value = "/templates/get", method = RequestMethod.POST, produces = "application/json")
    public String getTemplates(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.getTemplates called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.getTemplates();
            log.info("TasksController.getTemplates completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getTemplates request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get template filters")
    @RequestMapping(value = "/templates/getFilters", method = RequestMethod.POST, produces = "application/json")
    public String getFiltersTemplates(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.getFiltersTemplates called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.getFiltersTemplates();
            log.info("TasksController.getFiltersTemplates completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getFiltersTemplates request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Update templates")
    @RequestMapping(value = "/templates/update", method = RequestMethod.POST, produces = "application/json")
    public String getUpdateTemplates(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.getUpdateTemplates called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.getUpdateTemplates();
            log.info("TasksController.getUpdateTemplates completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getUpdateTemplates request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get tasks by filter")
    @RequestMapping(value = "/tasks/getByFilter", method = RequestMethod.POST, produces = "application/json")
    public String tasksGetByFilter(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.tasksGetByFilter called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.tasksByFilter(result);
            log.info("TasksController.tasksGetByFilter completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing tasksGetByFilter request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get tasks by filter (simple)")
    @RequestMapping(value = "/tasks/get", method = RequestMethod.POST, produces = "application/json")
    public String getTasksByFilter(@RequestBody String requestBody, HttpServletRequest request) {
        log.info("TasksController.getTasksByFilter called");
        log.debug("Request body: {}", requestBody);
        
        delayService.applyDelay(request.getRequestURI());
        String response = TasksJsonData.tasksGetByFilter();
        log.info("TasksController.getTasksByFilter completed");
        return response;
    }

    @Operation(summary = "Get task by ID")
    @RequestMapping(value = "/tasks/getTaskById", method = RequestMethod.POST, produces = "application/json")
    public String getTask(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.getTask called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.getTask(result);
            log.info("TasksController.getTask completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getTask request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get offers")
    @RequestMapping(value = "/tasks/offers", method = RequestMethod.POST, produces = "application/json")
    public String getOffer(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.getOffer called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.getOffer();
            log.info("TasksController.getOffer completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getOffer request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get filters")
    @RequestMapping(value = "/tasks/getFilters", method = RequestMethod.POST, produces = "application/json")
    public String getFilters(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.getFilters called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.getFilters();
            log.info("TasksController.getFilters completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getFilters request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get marking by ID")
    @RequestMapping(value = "/tasks/marking/getById", method = RequestMethod.POST, produces = "application/json")
    public String getMarkingById(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.getMarkingById called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.getMarkingById();
            log.info("TasksController.getMarkingById completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getMarkingById request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get premier divisions")
    @RequestMapping(value = "/tasks/data-dictionary-service/rest/pm/ver.4.0/getRows", method = RequestMethod.POST, produces = "application/json")
    public String premierDivisions(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.premierDivisions called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.premierDivisions();
            log.info("TasksController.premierDivisions completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing premierDivisions request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get result detail")
    @RequestMapping(value = "/values/get", method = RequestMethod.POST, produces = "application/json")
    public String resultDetailGet(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.resultDetailGet called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.resultDetailGet();
            log.info("TasksController.resultDetailGet completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing resultDetailGet request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Update result detail")
    @RequestMapping(value = "/values/update", method = RequestMethod.POST, produces = "application/json")
    public String resultDetailUpdate(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.resultDetailUpdate called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.resultDetailUpdate();
            log.info("TasksController.resultDetailUpdate completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing resultDetailUpdate request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get general counters")
    @RequestMapping(value = "/getGeneralCounters", method = RequestMethod.POST, produces = "application/json")
    public String getGeneralCounters(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.getGeneralCounters called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.getGeneralCounters();
            log.info("TasksController.getGeneralCounters completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getGeneralCounters request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get counters by clients")
    @RequestMapping(value = "/getCountersByClients", method = RequestMethod.POST, produces = "application/json")
    public String getCountersByClients(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.getCountersByClients called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.getCountersByClients();
            log.info("TasksController.getCountersByClients completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getCountersByClients request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get positions")
    @RequestMapping(value = "/tasks/positions/get", method = RequestMethod.POST, produces = "application/json")
    public String getPositions(HttpServletRequest request, HttpServletResponse response) {
        log.info("TasksController.getPositions called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay(request.getRequestURI());
            String responseData = TasksJsonData.getPositions();
            log.info("TasksController.getPositions completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getPositions request", e);
            return TasksJsonData.errorRequest();
        }
    }

    @Operation(summary = "Tasks main endpoint", description = "Main tasks endpoint handling various task operations")
    @RequestMapping(value = "/tasks", method = RequestMethod.POST, produces = "application/json")
    public String tasksMain(@RequestBody String requestBody, HttpServletRequest request) {
        log.info("TasksController.tasksMain called with request length: {}", requestBody.length());
        log.debug("Request body: {}", requestBody);
        
        String response;
        if (requestBody.contains("{\"method\":\"search\",\"id\":\"2\",\"jsonrpc\":\"2.0\"")) {
            response = TasksJsonData.search();
            log.debug("Returning search response");
        } else if (requestBody.contains("{\"ucpIds\":[1428105050034864952,1426354352652271068]}")) {
            response = TasksJsonData.tasksGetByFilter();
            log.debug("Returning tasks by filter");
        } else if (requestBody.contains("{\"result\":\"APPROVAL\",\"editor\":\"")) {
            response = TasksJsonData.taskUpdate();
            log.debug("Returning task update response");
        } else if ((requestBody.contains("\"plannedTeamId\":\"")) && (requestBody.contains("\"processType\":\"VIP2MASS\""))) {
            response = TasksJsonData.getMarkingCreate();
            log.debug("Returning marking create response");
        } else if ((requestBody.startsWith("{\"epkId\":")) && (requestBody.length() < 35)) {
            response = TasksJsonData.createMarking();
            log.debug("Returning create marking response");
        } else if (requestBody.contains("\"divisionCode\":\"13859200020\"")) {
            response = TasksJsonData.getFreeEmployee();
            log.debug("Returning free employee response");
        } else if (requestBody.length() == 0) {
            response = TasksJsonData.errorRequest();
            log.warn("Empty request body received");
        } else {
            response = TasksJsonData.search();
            log.debug("Default response: search");
        }
        
        delayService.applyDelay(request.getRequestURI());
        log.info("TasksController.tasksMain completed");
        return response;
    }

    @Operation(summary = "Get teams for tasks")
    @RequestMapping(value = "/tasks/teams/get", method = RequestMethod.POST, produces = "application/json")
    public String getTeams(@RequestBody String requestBody, HttpServletRequest request) {
        log.info("TasksController.getTeams called");
        log.debug("Request body: {}", requestBody);
        
        String response;
        if ((requestBody.contains("\"type\":\"DEF\"")) && (requestBody.contains("\"status\":[\"ACTIVE\",\"INACTIVE\"]"))
                || (requestBody.contains("{\"channel\":\"VIP\",\"kmCode\":[],\"id\":["))) {
            response = TasksJsonData.getTeams();
            log.debug("Returning teams data");
        } else if ((requestBody.contains("\"kmCode\":[]")) && (requestBody.contains("\"status\":[]"))) {
            response = TasksJsonData.getTeams();
            log.debug("Returning teams data");
        } else if (requestBody.contains("{\"divisionCodeOwner\":\"38903800566\",\"kmCode\":[],\"id\":[],\"status\":[\"ACTIVE\"]}")) {
            response = TasksJsonData.getDivisionTeams();
            log.debug("Returning division teams");
        } else if (requestBody.contains("{\"divisionCodeOwner\":\"38903800566\",\"channel\":\"VIP\",\"kmCode\":[],\"id\":[],\"type\":\"DEF\",\"status\":[\"ACTIVE\"]}")) {
            response = TasksJsonData.getDivisionTeams();
            log.debug("Returning division teams");
        } else if (requestBody.length() == 0) {
            response = TasksJsonData.errorRequest();
            log.warn("Empty request body received");
        } else {
            response = TasksJsonData.getTeams();
            log.debug("Default teams response");
        }
        
        delayService.applyDelay(request.getRequestURI());
        log.info("TasksController.getTeams completed");
        return response;
    }

    @Operation(summary = "Check marking")
    @RequestMapping(value = "/tasks/marking/check", method = RequestMethod.POST, produces = "application/json")
    public String checkMarking(@RequestBody String requestBody, HttpServletRequest request) {
        log.info("TasksController.checkMarking called");
        log.debug("Request body: {}", requestBody);
        
        String response;
        if (requestBody.length() == 0) {
            response = TasksJsonData.errorRequest();
            log.warn("Empty request body received");
        } else if ((requestBody.startsWith("{\"epkId\":")) && (requestBody.length() < 35)) {
            response = TasksJsonData.getMarkingCheck();
            log.debug("Returning marking check response");
        } else {
            response = TasksJsonData.getMarkingCheck();
            log.debug("Default marking check response");
        }
        
        delayService.applyDelay(request.getRequestURI());
        log.info("TasksController.checkMarking completed");
        return response;
    }

    @Operation(summary = "Get free teams")
    @RequestMapping(value = "/tasks/teams/free", method = RequestMethod.POST, produces = "application/json")
    public String getFreeTeams(@RequestBody String requestBody, HttpServletRequest request) {
        log.info("TasksController.getFreeTeams called");
        log.debug("Request body: {}", requestBody);
        
        delayService.applyDelay(request.getRequestURI());
        String response = TasksJsonData.getFreeTeams();
        log.info("TasksController.getFreeTeams completed");
        return response;
    }

    @Operation(summary = "Get marking by client")
    @RequestMapping(value = "/tasks/marking/getByClient", method = RequestMethod.POST, produces = "application/json")
    public String getMarkingByClient(@RequestBody String requestBody, HttpServletRequest request) {
        log.info("TasksController.getMarkingByClient called");
        log.debug("Request body: {}", requestBody);
        
        String response;
        if (requestBody.length() == 0) {
            response = TasksJsonData.errorRequest();
            log.warn("Empty request body received");
        } else if ((requestBody.startsWith("{\"epkId\":")) && (requestBody.length() < 35)) {
            response = TasksJsonData.getMarkingByClient();
            log.debug("Returning marking by client response");
        } else {
            response = TasksJsonData.getMarkingByClient();
            log.debug("Default marking by client response");
        }
        
        delayService.applyDelay(request.getRequestURI());
        log.info("TasksController.getMarkingByClient completed");
        return response;
    }

}