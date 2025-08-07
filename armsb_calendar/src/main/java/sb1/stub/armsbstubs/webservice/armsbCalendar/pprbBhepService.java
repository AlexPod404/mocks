package sb1.stub.armsbstubs.webservice.armsbCalendar;
import sb1.stub.armsbstubs.data.armsbCalendar.calendarJson;
import sb1.stub.armsbstubs.data.armsbCalendar.pprbBhepJson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/employee")
public class pprbBhepService {

    static long delta = 100L;
    static String status = "OK";
    @Autowired
    private ObjectMapper objectMapper;

    //
    @RequestMapping(value = "/com.sbt.bpspe.core.json.rpc.api.Employee", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> getEmployee(@RequestBody String request) throws InterruptedException {
        if (status.contains("BAD_REQUEST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"comment\": \"The stub doesn't work\"}");
        }
        try {
            JsonNode node = objectMapper.readTree(request);
            Thread.sleep(delta);
            // Проверяем наличие и ненулевую длину массива перед обращением к элементу
            //String personalNumber = node.get("values").isArray() && !node.get("values").isEmpty() ?
            //        node.get("values").get(0).asText() : "59482376";

            ZonedDateTime oneHourBefore = ZonedDateTime.now().minusHours(1).withZoneSameInstant(ZoneOffset.UTC);
            String lastData = DateTimeFormatter.ISO_INSTANT.format(oneHourBefore.toInstant());
            String response = pprbBhepJson.getSingleEmployeeActivities()
                    //.replace("${personalNumber}", personalNumber)
                    .replace("${lastData}", lastData);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }catch (Exception e) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"endpoint\": \"/com.sbt.bpspe.core.json.rpc.api.Employee\", \"error\": \"Server error\", \"comment\": \"Не удалось распарсить тело запроса.\"}");
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
