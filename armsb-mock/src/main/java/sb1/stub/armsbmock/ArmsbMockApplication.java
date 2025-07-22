package sb1.stub.armsbmock;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "ARMSB Mock Services API",
        version = "1.0.0",
        description = "Consolidated mock services for ARMSB subsystems: Clients, Tasks, CTI, Client Card"
    )
)
public class ArmsbMockApplication {

    public static void main(String[] args) {
        log.info("Starting ARMSB Mock Services Application...");
        SpringApplication.run(ArmsbMockApplication.class, args);
        log.info("ARMSB Mock Services Application started successfully");
    }

}