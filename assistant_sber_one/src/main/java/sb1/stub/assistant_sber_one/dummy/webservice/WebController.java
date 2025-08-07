package sb1.stub.assistant_sber_one.dummy.webservice;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import sb1.stub.assistant_sber_one.dummy.data.hardcode.Jsons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class WebController {

    static long delta = 100L;

    @SneakyThrows(Exception.class)
    @PostMapping(value = "/sbpcontext/v1/context", produces = "application/json")
    public String getSbpContext(HttpServletRequest request, HttpServletResponse response){
        String result = IOUtils.toString(request.getInputStream()); // ПОЛУЧАЕМ СТРОКУ ИЗ ТЕЛА ЗАПРОСА
        //log.info("Request to /sbpcontext/v1/context: " + result);
        Thread.sleep(delta);
        //log.info("Response from /sbpcontext/v1/context: " + Jsons.getSbpContextInit());
        return Jsons.getSbpContextInit();
    }

    @SneakyThrows(Exception.class)
    @GetMapping(value = "/sbpemployeeinfo/v1/employee", produces = "application/json")
    public String getEmployeeInfo(HttpServletRequest request, HttpServletResponse response){
        String result = IOUtils.toString(request.getInputStream()); // ПОЛУЧАЕМ СТРОКУ ИЗ ТЕЛА ЗАПРОСА
        //log.info("Request to /sbpemployeeinfo/v1/employee: " + result);
        Thread.sleep(delta);
        //log.info("Response from /sbpemployeeinfo/v1/employee: " + Jsons.getEmployeeInfo());
        return Jsons.getEmployeeInfo();
    }

    @RequestMapping(value = "/setDelta/{delta}")
    @ResponseBody
    public String setDelta(@PathVariable String delta) {
        WebController.delta = Long.parseLong(delta, 10);
        return "Set delta to: " + WebController.delta + "\n";
    }

    @GetMapping(value = "/getDelta")
    @ResponseBody
    public String getDelta() {
        return "Delta is: " + WebController.delta + "\n";
    }
}
