package sb1.stub.armsbstubs.webservice.armsbCalendar;
import sb1.stub.armsbstubs.data.armsbCalendar.pprbChatJson;

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
@RequestMapping("/v1/chats")
public class pprbChatService {

    static long delta = 100L;
    static String status = "OK";

    @Autowired
    private ObjectMapper objectMapper;

    /*СУП -- */
    // /sendExternalMessage/
    @RequestMapping(value = "/sendExternalMessage", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> sendExternalMessage(@RequestBody String request) throws InterruptedException {
        if (status.contains("BAD_REQUEST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"comment\": \"The stub doesn't work\"}");
        }
        Thread.sleep(delta);
        String response = pprbChatJson.SendExternalMessage();
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
