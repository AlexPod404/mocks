package sb1.stub.armsbstubs.webservice.armsbCalendar;
import sb1.stub.armsbstubs.data.armsbCalendar.calendarJson;

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
@RequestMapping("/calendarservice")
public class calendarService {

    static long delta = 100L;
    static String status = "OK";
    @Autowired
    private ObjectMapper objectMapper;

    // /api/v1/activities/get
    @RequestMapping(value = "/api/v1/activities/get", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> getActivities(@RequestBody String request) throws InterruptedException {
        if (status.contains("BAD_REQUEST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"comment\": \"The stub doesn't work\"}");
        }
        try {
            JsonNode node = objectMapper.readTree(request);
            Thread.sleep(delta);
            // Проверяем наличие и ненулевую длину массива перед обращением к элементу
            String positionId = node.get("positionIds").isArray() && !node.get("positionIds").isEmpty() ?
                    node.get("positionIds").get(0).asText() : "935c04eb-135b-482b-87eb-82fe34749091";

            String response = calendarJson.GetActivities()
                    .replace("${positionId}", positionId);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }catch (Exception e) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"endpoint\": \"/api/v1/activities/get\", \"error\": \"Server error\", \"comment\": \"Не удалось распарсить тело запроса.\"}");
        }
    }

    @RequestMapping(value = "/api/v1/activities/new", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> newActivities(@RequestBody String request) throws InterruptedException {
        if (status.contains("BAD_REQUEST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"comment\": \"The stub doesn't work\"}");
        }
        Thread.sleep(delta);
        String response = calendarJson.NewActivities();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }

    @RequestMapping(value = "/api/v1/activities/update", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> updateActivities(@RequestBody String request) throws InterruptedException {
        if (status.contains("BAD_REQUEST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"comment\": \"The stub doesn't work\"}");
        }
        Thread.sleep(delta);
        String response = calendarJson.UpdateActivities();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }

    // /api/v1/dictionary/get
    @RequestMapping(value = "/api/v1/dictionary/get", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> getDictionary(@RequestBody String request) throws InterruptedException {
        if (status.contains("BAD_REQUEST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"comment\": \"The stub doesn't work\"}");
        }
        String response = "";
        try {
            JsonNode node = objectMapper.readTree(request);
            Thread.sleep(delta);

            String name = node.get("name").asText();
            switch (name) {
                case "ACTIVITY_STATUSES":
                    response = calendarJson.GetDictionaryActivityStatuses(); break;
                case "ACTIVITY_TYPES":
                    response = calendarJson.GetDictionaryActivityTypes(); break;
                default:
                    return ResponseEntity.status( HttpStatus.BAD_REQUEST)
                            .body("{\"endpoint\": \"/api/v1/activities/get\", \"comment\": \"Не верное значение параметра name.\"}");
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }catch (Exception e) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"endpoint\": \"/api/v1/activities/get\", \"error\": \"Server error\", \"comment\": \"Не удалось распарсить тело запроса.\"}");
        }
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
