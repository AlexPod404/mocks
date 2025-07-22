package sb1.stub.armsbmock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;
import sb1.stub.armsbmock.data.ClientCardJsonData;
import sb1.stub.armsbmock.service.DelayService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping
@Tag(name = "ClientCard", description = "ARMSB Client Card subsystem endpoints")
@ConditionalOnProperty(prefix = "armsb.mock.client-card", name = "enabled", havingValue = "true", matchIfMissing = true)
public class ClientCardController {

    @Autowired
    private DelayService delayService;

    @Operation(summary = "Get employee information for client card")
    @RequestMapping(value = "/clientcard/sbpemployeeinfo/v1/employee", method = RequestMethod.GET, produces = "application/json")
    public String getEmployee() {
        log.info("ClientCardController.getEmployee called");
        delayService.applyDelay();
        String response = ClientCardJsonData.employee();
        log.info("ClientCardController.getEmployee completed");
        return response;
    }

    @Operation(summary = "Get client positions", description = "Get client positions and accounts")
    @RequestMapping(value = "/clientcard/positions/get", method = RequestMethod.POST, produces = "application/json")
    public String getByClient(HttpServletRequest request, HttpServletResponse response) {
        log.info("ClientCardController.getByClient called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay();
            String responseData = ClientCardJsonData.getClientCard_new();
            log.info("ClientCardController.getByClient completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getByClient request", e);
            return ClientCardJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get SBP context", description = "Initialize SBP context for client operations")
    @RequestMapping(value = "/clients/getClientCardFromCRMandEPK/rest/v1/context", method = RequestMethod.POST, produces = "application/json")
    public String getSbpContext(HttpServletRequest request, HttpServletResponse response) {
        log.info("ClientCardController.getSbpContext called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay();
            String responseData = ClientCardJsonData.getSbpContextInit();
            log.info("ClientCardController.getSbpContext completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getSbpContext request", e);
            return ClientCardJsonData.errorRequest();
        }
    }

    @Operation(summary = "Employee service for send operations")
    @RequestMapping(value = "/clientcard/employee/com.sbt.bpspe.core.json.rpc.api.Employee", method = RequestMethod.POST, produces = "application/json")
    public String forSend(HttpServletRequest request, HttpServletResponse response) {
        log.info("ClientCardController.forSend called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay();
            String responseData = ClientCardJsonData.forSend();
            log.info("ClientCardController.forSend completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing forSend request", e);
            return ClientCardJsonData.errorRequest();
        }
    }

    @Operation(summary = "Send operation", description = "Send client card operations")
    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json")
    public String send(HttpServletRequest request, HttpServletResponse response) {
        log.info("ClientCardController.send called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay();
            String responseData = ClientCardJsonData.send();
            log.info("ClientCardController.send completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing send request", e);
            return ClientCardJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get companies", description = "Get company information")
    @RequestMapping(value = "/getCompanies", method = RequestMethod.POST, produces = "application/json")
    public String getCompanies(HttpServletRequest request, HttpServletResponse response) {
        log.info("ClientCardController.getCompanies called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay();
            String responseData = ClientCardJsonData.getCompanies();
            log.info("ClientCardController.getCompanies completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getCompanies request", e);
            return ClientCardJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get company info by ID", description = "Get detailed company information by ID")
    @RequestMapping(value = "/getInfoByCompanyId", method = RequestMethod.POST, produces = "application/json")
    public String getInfoByCompanyId(HttpServletRequest request, HttpServletResponse response) {
        log.info("ClientCardController.getInfoByCompanyId called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay();
            String responseData = ClientCardJsonData.getInfoByCompanyId();
            log.info("ClientCardController.getInfoByCompanyId completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getInfoByCompanyId request", e);
            return ClientCardJsonData.errorRequest();
        }
    }

    @Operation(summary = "Get teams for client card")
    @RequestMapping(value = "/clientcard/teams/get", method = RequestMethod.POST, produces = "application/json")
    public String getTeamsGet(HttpServletRequest request, HttpServletResponse response) {
        log.info("ClientCardController.getTeamsGet called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay();
            String responseData = ClientCardJsonData.getTeamsGet();
            log.info("ClientCardController.getTeamsGet completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing getTeamsGet request", e);
            return ClientCardJsonData.errorRequest();
        }
    }

    @Operation(summary = "Client card operations", description = "Main client card endpoint handling various operations")
    @RequestMapping(value = "/clients/getClientCardFromCRMandEPK", method = RequestMethod.POST, produces = "application/json")
    public String getClientCard(@RequestBody String requestBody) {
        log.info("ClientCardController.getClientCard called with request length: {}", requestBody.length());
        log.debug("Request body: {}", requestBody);
        
        String response;
        
        // getClientCard from CRM
        if (requestBody.contains("urn:sbrfsystems:99-crm")) {
            response = ClientCardJsonData.getClientCardFromCRM();
            log.debug("Returning client card from CRM");
        }
        // getClientCard from EPK
        else if (requestBody.contains("\"ucpId\":") && !requestBody.contains("updateDateTime") && !requestBody.contains("ARMSB_NOTIFICATION")) {
            response = ClientCardJsonData.getClientCardFromEPK_ORGIGINAL();
            log.debug("Returning client card from EPK");
        }
        // getClientSatellite
        else if (requestBody.contains("ucpIdSatellite")) {
            response = ClientCardJsonData.getAddDelSatellite();
            log.debug("Returning satellite from EPK");
        }
        // update
        else if (requestBody.contains("updateDateTime")) {
            response = ClientCardJsonData.getUpdateCard();
            log.debug("Returning update card response");
        }
        // addSatellite + removeSatellite
        else if (requestBody.contains("servicedVipVsp")) {
            response = ClientCardJsonData.getAddDelSatellite();
            log.debug("Returning add/del satellite response");
        }
        // notes/update
        else if (requestBody.contains("LoadRunner obnovlenie notes")) {
            response = ClientCardJsonData.getNotesUpdate();
            log.debug("Returning notes update response");
        }
        // notes/get
        else if (requestBody.contains("\"pagination\":{\"limit\":10}")) {
            response = ClientCardJsonData.getNotesGet();
            log.debug("Returning notes get response");
        }
        // getAdditionalInfo
        else if ((requestBody.startsWith("{\"epkId\":")) && (requestBody.length() < 32)) {
            response = ClientCardJsonData.getAdditionalInfo();
            log.debug("Returning additional info response");
        }
        // teams/get
        else if (requestBody.contains("{\"channel\":\"VIP\",\"kmCode\":[],\"id\":[\"c0888085-701d-441b-a344-21d588621673\"]}") ||
                 requestBody.contains("{\"kmCode\":[],\"id\":[\"9c13e675-7760-47bd-b90e-5ba440c1fdb2\"],\"staffUnitId\":[]," +
                                     "\"status\":[],\"ossPositionCode\":[]}") ||
                 requestBody.contains("{\"kmCode\":[],\"id\":[")) {
            response = ClientCardJsonData.getTeamsGet();
            log.debug("Returning teams get response");
        }
        // getTeamsGet
        else if ((requestBody.startsWith("{\"kmCode\":[],\"id\":[\"")) &&
                 (requestBody.endsWith("\"],\"status\":[]}")) &&
                 (!requestBody.contains("a10945a4-cfde-4531-acdb-d10f04a97d3b")) &&
                 (requestBody.length() < 72)) {
            response = ClientCardJsonData.getTeamsGet();
            log.debug("Returning teams get response");
        }
        // updateAdditionalInfo
        else if ((requestBody.contains("{\"criteriaId\":\"8051b304-73a4-4907-abad-82b4eafece39\",\"changedBy\":\"")) &&
                 (requestBody.contains("\",\"epkId\":\""))) {
            response = ClientCardJsonData.getUpdateAdditionalInfo();
            log.debug("Returning update additional info response");
        }
        // getMarkingTeam
        else if (requestBody.contains("{\"kmCode\":[],\"id\":[\"a10945a4-cfde-4531-acdb-d10f04a97d3b\"],\"staffUnitId\":[]," +
                                      "\"status\":[],\"ossPositionCode\":[]}")) {
            response = ClientCardJsonData.getMarkingTeam();
            log.debug("Returning marking team response");
        }
        // notification offer accept
        else if (requestBody.contains("{\"sysSenderUrn\":\"urn:sbrfsystems:99-ufs-sr\",\"subSystemCode\":\"ARMSB_NOTIFICATION\"")) {
            if (requestBody.contains("\"packageId\":\"")) {
                response = ClientCardJsonData.notifOfferAccept2();
                log.debug("Returning notification offer accept 2 response");
            } else {
                response = ClientCardJsonData.notifOfferAccept();
                log.debug("Returning notification offer accept response");
            }
        }
        // sbpContextInit
        else if (requestBody.contains("{\"sbpServiceFormat\":\"REMOTE\",\"sbpFormatType\":\"APPEAL\",\"dt\":\"")) {
            response = ClientCardJsonData.sbpContextInit();
            log.debug("Returning SBP context init response");
        }
        // encryptor/qliksense
        else if (requestBody.contains("\"RqUID\":\"") && requestBody.contains("\"SPName\":\"") && requestBody.contains("\"hash_salt_epk_id\":\"")) {
            response = ClientCardJsonData.qliksense();
            log.debug("Returning qliksense response");
        }
        // getEmployeeSession
        else if (requestBody.contains("{\"channel\":\"VIP\",\"kmCode\":[],\"id\":[],\"type\":\"DEF\",\"employeeNumber\":\"")) {
            response = ClientCardJsonData.getInfoClient();
            log.debug("Returning info client response");
        }
        // getTypedTeams2
        else if (requestBody.contains("{\"channel\":\"VIP\",\"kmCode\":[],\"id\":[],\"type\":\"DEF\",\"staffUnitId\":[]," +
                                      "\"status\":[\"ACTIVE\"],\"ossPositionCode\":[\"20010601\"]}")) {
            response = ClientCardJsonData.getTypedTeams2();
            log.debug("Returning typed teams 2 response");
        }
        // getTypedTeams3
        else if (requestBody.contains("{\"method\":\"searchAssignment\",\"id\":\"2\",\"jsonrpc\":\"2.0\"," +
                                      "\"params\":[{\"pagination\":{\"offset\":0.0,\"limit\":1.0},\"parameters\":[{\"modifier\":\"IN\",")) {
            response = ClientCardJsonData.getTypedTeams3();
            log.debug("Returning typed teams 3 response");
        }
        // teams_addLink
        else if (requestBody.contains("\",\"teamId\":\"e47346a1-7048-4576-9523-df395f214fc1\"," +
                                      "\"linkTypeId\":\"d5ab0589-70f3-4200-95ab-1d16c14a01c1\",\"changedBy\":\"")) {
            response = ClientCardJsonData.teams_addLink();
            log.debug("Returning teams add link response");
        }
        // teams_id
        else if (requestBody.contains("{\"id\": [\"")) {
            response = ClientCardJsonData.teams_id();
            log.debug("Returning teams id response");
        }
        // teams_unlinkTeam
        else if (requestBody.contains("\"epkId\":\"") && requestBody.contains("\",\"changedBy\":\"") &&
                 requestBody.contains(",\"linkTypeId\":[],\"links\":[\"cf623bd8-bd7f-4bb5-9055-8daea22ca7cc\"]}")) {
            response = ClientCardJsonData.teams_unlinkTeam();
            log.debug("Returning teams unlink team response");
        }
        else if (requestBody.length() == 0) {
            response = ClientCardJsonData.errorRequest();
            log.warn("Empty request body received");
        }
        else {
            response = ClientCardJsonData.getClientCardFromEPK_ORGIGINAL();
            log.debug("Default response: client card from EPK");
        }
        
        delayService.applyDelay();
        log.info("ClientCardController.getClientCard completed");
        return response;
    }

    @Operation(summary = "Create ECM structure", description = "Create ECM file structure")
    @RequestMapping(value = "/services/create-structure", method = RequestMethod.POST, produces = "application/json")
    public String createStructure(HttpServletRequest request, HttpServletResponse response) {
        log.info("ClientCardController.createStructure called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay();
            String responseData = ClientCardJsonData.createStructure();
            log.info("ClientCardController.createStructure completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing createStructure request", e);
            return ClientCardJsonData.errorRequest();
        }
    }

    @Operation(summary = "Set NetScan region config", description = "Set region configuration for NetScan file attachments")
    @RequestMapping(value = "/sbolpro/netscanbh/v1/file/setRegionConfig", method = RequestMethod.POST, produces = "application/json")
    public String setRegionConfig(HttpServletRequest request, HttpServletResponse response) {
        log.info("ClientCardController.setRegionConfig called");
        try {
            String result = IOUtils.toString(request.getInputStream());
            log.debug("Request body: {}", result);
            delayService.applyDelay();
            String responseData = ClientCardJsonData.setRegionConfig();
            log.info("ClientCardController.setRegionConfig completed");
            return responseData;
        } catch (Exception e) {
            log.error("Error processing setRegionConfig request", e);
            return ClientCardJsonData.errorRequest();
        }
    }

}