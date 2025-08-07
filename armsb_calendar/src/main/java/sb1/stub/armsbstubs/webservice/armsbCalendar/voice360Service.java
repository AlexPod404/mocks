package sb1.stub.armsbstubs.webservice.armsbCalendar;
import sb1.stub.armsbstubs.data.armsbCalendar.voice360Json;

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
//@RequestMapping("/v2/chat_bot")
public class voice360Service {
    static long delta = 100L;
    static String status = "OK";

    @Autowired
    private ObjectMapper objectMapper;

    /*СУП --ufs.baseurl.voice360service */
    // /v2/chat_bot/composite_v2/
    @RequestMapping(value = "/v2/chat_bot/composite_v2", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> getByTeamId(@RequestBody String request) throws InterruptedException {
        if (status.contains("BAD_REQUEST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"comment\": \"The stub doesn't work\"}");
        }
        Thread.sleep(delta);
        String response = voice360Json.CompositeV2();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
    // /ekts/speech/new/
    @RequestMapping(value = "/ekts/speech/new", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> speechNew(@RequestBody String request) throws InterruptedException {
        if (status.contains("BAD_REQUEST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"comment\": \"The stub doesn't work\"}");
        }
        Thread.sleep(delta);
        String response = voice360Json.SpeechNew();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
    // /ekts/speech/get/
    @RequestMapping(value = "/ekts/speech/get", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> speechGet(@RequestBody String request) throws InterruptedException {
        if (status.contains("BAD_REQUEST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"comment\": \"The stub doesn't work\"}");
        }
        Thread.sleep(delta);
        String response = voice360Json.SpeechGet();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }





    @RequestMapping(value = "/voice360/getDelta", method = RequestMethod.GET)
    @ResponseBody
    public String getDelta() {
        return "Delta is: " + teamService.delta + "\n";
    }
    @RequestMapping(value = "/voice360/setDelta/{delta}")
    @ResponseBody
    public String setDelta(@PathVariable String delta) {
        teamService.delta = Long.parseLong(delta, 10);
        return "Set delta to: " + teamService.delta + "\n";
    }
    @RequestMapping(value = "/voice360/setStatus/{status}")
    @ResponseBody
    public String setStatus(@PathVariable String status) {
        teamService.status = status;
        return "Set status to: " + teamService.status + "\n";
    }
}
