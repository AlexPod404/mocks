package sb1.stub.armsbmock.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "armsb.mock")
public class ArmsbMockConfig {
    
    private long defaultDelay = 100L;
    
    private Map<String, Long> delays = new HashMap<>();
    
    private Clients clients = new Clients();
    private Tasks tasks = new Tasks();
    private Cti cti = new Cti();
    private ClientCard clientCard = new ClientCard();
    
    @Data
    public static class Clients {
        private boolean enabled = true;
    }
    
    @Data 
    public static class Tasks {
        private boolean enabled = true;
    }
    
    @Data
    public static class Cti {
        private boolean enabled = true;
    }
    
    @Data
    public static class ClientCard {
        private boolean enabled = true;
    }
}