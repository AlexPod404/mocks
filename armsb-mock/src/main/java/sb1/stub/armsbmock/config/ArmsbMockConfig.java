package sb1.stub.armsbmock.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "armsb.mock")
public class ArmsbMockConfig {
    
    private long defaultDelay = 100L;
    
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