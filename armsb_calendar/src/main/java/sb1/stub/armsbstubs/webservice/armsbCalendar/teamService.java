package sb1.stub.armsbstubs.webservice.armsbCalendar;
import sb1.stub.armsbstubs.data.armsbCalendar.teamServiceJson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/teamsservice")
public class teamService {

    static long delta = 100L;
    static String status = "OK";

    @Autowired
    private ObjectMapper objectMapper;

    /*СУП -- */
    //getPositions
    @RequestMapping(value = "/api/v1/positions/get", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> getPositions(@RequestBody String request) throws InterruptedException {
        if (status.contains("BAD_REQUEST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"comment\": \"The stub doesn't work\"}");
        }
        try {
            JsonNode node = objectMapper.readTree(request);
            Thread.sleep(delta);
            // Проверяем наличие и ненулевую длину массива перед обращением к элементу
            String staffUnitId = node.get("staffUnitId").isArray() && !node.get("staffUnitId").isEmpty() ?
                    node.get("staffUnitId").get(0).asText() : "48efcffc-20d4-4a32-bdd4-2ada625c7df5";
            String divisionCode = node.get("divisionCode").isArray() && !node.get("divisionCode").isEmpty() ?
                    node.get("divisionCode").get(0).asText() : "52861923399";
            String employeeNumber = node.get("employeeNumber").isArray() && !node.get("employeeNumber").isEmpty() ?
                    node.get("employeeNumber").get(0).asText() : "59482376";

            String response = teamServiceJson.getPositions()
                    .replace("${staffUnitId}", staffUnitId)
                    .replace("${divisionCode}", divisionCode)
                    .replace("${employeeNumber}", employeeNumber);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }catch (Exception e) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"endpoint\": \"/api/v1/positions/get\", \"error\": \"Server error\", \"comment\": \"Не удалось распарсить тело запроса.\"}");
        }
    }

    /*СУП -- */
    //getByTeamId
    @RequestMapping(value = "/api/v1/clients/getByTeamId", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> getByTeamId(@RequestBody String request) throws InterruptedException {
        if (status.contains("BAD_REQUEST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"comment\": \"The stub doesn't work\"}");
        }
        Thread.sleep(delta);
        String response = teamServiceJson.GetByTeamId();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }










    @RequestMapping(value = "/getDelta", method = RequestMethod.GET)
    @ResponseBody
    public String getDelta() {
        return "Delta is: " + teamService.delta + "\n";
    }
    @RequestMapping(value = "/setDelta/{delta}")
    @ResponseBody
    public String setDelta(@PathVariable String delta) {
        teamService.delta = Long.parseLong(delta, 10);
        return "Set delta to: " + teamService.delta + "\n";
    }
    @RequestMapping(value = "/setStatus/{status}")
    @ResponseBody
    public String setStatus(@PathVariable String status) {
        teamService.status = status;
        return "Set status to: " + teamService.status + "\n";
    }
}
