package sb1.stub.armsbstubs.webservice.armsbCalendar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import sb1.stub.armsbstubs.data.armsbCalendar.pprbBhepJson;
import sb1.stub.armsbstubs.data.armsbCalendar.pprbMyOfficeJson;
import sb1.stub.armsbstubs.data.armsbCalendar.teamServiceJson;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/my-office")
public class pprbMyOfficeService {

    static long delta = 100L;
    static String status = "OK";
    @Autowired
    private ObjectMapper objectMapper;

    /*pprbMyOfficeRs - СУП:ufs.baseurl.pprbMyOfficeService*/
    @RequestMapping(value = "/api/v1/sbolpro/photo/{any}", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> myOffice(@RequestBody String request) throws InterruptedException {
        if (status.contains("BAD_REQUEST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"comment\": \"The stub doesn't work\"}");
        }
        Thread.sleep(delta);
        String response = pprbMyOfficeJson.pprbMyOffice();
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
