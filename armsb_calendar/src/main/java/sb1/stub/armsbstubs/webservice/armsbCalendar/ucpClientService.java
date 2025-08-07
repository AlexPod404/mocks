package sb1.stub.armsbstubs.webservice.armsbCalendar;
import sb1.stub.armsbstubs.data.armsbCalendar.ucpClientJson;

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
@RequestMapping("/armsb/ucp/clients")
public class ucpClientService {

    static long delta = 100L;
    static String status = "OK";
    @Autowired
    private ObjectMapper objectMapper;

    // /armsb/ucp/clients/get
    @RequestMapping(value = "/get", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> getClient(@RequestBody String request) throws InterruptedException {
        if (status.contains("BAD_REQUEST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"comment\": \"The stub doesn't work\"}");
        }
        try {
            JsonNode node = objectMapper.readTree(request);
            Thread.sleep(delta);
            // Проверяем наличие и ненулевую длину массива перед обращением к элементу
            String ucpId = node.get("ucpIds").isArray() && !node.get("ucpIds").isEmpty() ?
                    node.get("ucpIds").get(0).asText() : "1955817781818186038";

            String response = ucpClientJson.ClientGet()
                    .replace("${id}", ucpId);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }catch (Exception e) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"endpoint\": \"/armsb/ucp/clients/get\", \"error\": \"Server error\", \"comment\": \"Не удалось распарсить тело запроса.\"}");
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
