package sb1.stub.armsbmock.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sb1.stub.armsbmock.config.ArmsbMockConfig;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DelayServiceTest {

    @Mock
    private ArmsbMockConfig config;

    @InjectMocks
    private DelayService delayService;

    private Map<String, Long> configuredDelays;

    @BeforeEach
    void setUp() {
        configuredDelays = new HashMap<>();
        configuredDelays.put("/clients/srvgetclientlist", 250L);
        configuredDelays.put("/cti/getCommunications", 400L);
        
        // Use lenient to avoid unnecessary stubbing errors
        lenient().when(config.getDefaultDelay()).thenReturn(300L);
        lenient().when(config.getDelays()).thenReturn(configuredDelays);
    }

    @Test
    void testInitializeEndpointDelays() {
        // When DelayService initializes
        delayService.initializeEndpointDelays();
        
        // Then all endpoints should have delays configured
        Map<String, Long> allDelays = delayService.getAllEndpointDelays();
        
        // Verify that configured delays are preserved
        assertEquals(250L, allDelays.get("/clients/srvgetclientlist"));
        assertEquals(400L, allDelays.get("/cti/getCommunications"));
        
        // Verify that endpoints without configured delays get default delay
        assertTrue(allDelays.containsKey("/tasks/getByFilter"));
        assertNotNull(allDelays.get("/tasks/getByFilter"));
    }

    @Test
    void testGetDelayForEndpointWithConfiguredDelay() {
        delayService.initializeEndpointDelays();
        
        // When getting delay for a configured endpoint
        Long delay = delayService.getDelayForEndpoint("/clients/srvgetclientlist", true);
        
        // Then it should return the configured delay
        assertEquals(250L, delay);
    }

    @Test
    void testGetDelayForEndpointWithoutConfiguredDelay() {
        delayService.initializeEndpointDelays();
        
        // When getting delay for an endpoint without explicit configuration
        Long delay = delayService.getDelayForEndpoint("/tasks/getByFilter", true);
        
        // Then it should return the default delay
        assertEquals(300L, delay);
    }

    @Test
    void testSetDelayForEndpoint() {
        delayService.initializeEndpointDelays();
        
        // When setting a new delay for an endpoint
        String endpoint = "/tasks/getByFilter";
        long newDelay = 500L;
        delayService.setDelayForEndpoint(endpoint, newDelay);
        
        // Then the delay should be updated
        Long actualDelay = delayService.getDelayForEndpoint(endpoint, true);
        assertEquals(newDelay, actualDelay);
    }

    @Test
    void testGlobalDelayFunctionality() {
        // When setting global delay (this test doesn't use mocked config)
        long globalDelay = 1000L;
        delayService.setGlobalDelta(globalDelay);
        
        // Then the global delay should be returned
        assertEquals(globalDelay, delayService.getGlobalDelta());
    }

    @Test
    void testGetAllEndpointDelays() {
        delayService.initializeEndpointDelays();
        
        // When getting all endpoint delays
        Map<String, Long> allDelays = delayService.getAllEndpointDelays();
        
        // Then it should include both configured and runtime delays
        assertNotNull(allDelays);
        assertFalse(allDelays.isEmpty());
        assertTrue(allDelays.containsKey("/clients/srvgetclientlist"));
        assertTrue(allDelays.containsKey("/cti/getCommunications"));
    }

    @Test
    void testDelayPriorityOrder() {
        delayService.initializeEndpointDelays();
        
        String endpoint = "/test/endpoint";
        long runtimeDelay = 600L;
        
        // Set runtime delay
        delayService.setDelayForEndpoint(endpoint, runtimeDelay);
        
        // Add same endpoint to configuration with different value
        configuredDelays.put(endpoint, 700L);
        
        // Runtime delay should take priority
        Long actualDelay = delayService.getDelayForEndpoint(endpoint, true);
        assertEquals(runtimeDelay, actualDelay);
    }

    @Test
    void testRemoveDelayForEndpoint() {
        delayService.initializeEndpointDelays();
        
        String endpoint = "/test/endpoint";
        long delay = 500L;
        
        // Set a delay
        delayService.setDelayForEndpoint(endpoint, delay);
        assertEquals(delay, delayService.getDelayForEndpoint(endpoint, true));
        
        // Remove the delay
        delayService.removeDelayForEndpoint(endpoint);
        
        // Should fall back to default or global delay
        Long actualDelay = delayService.getDelayForEndpoint(endpoint, true);
        assertNotEquals(delay, actualDelay);
    }

    @Test
    void testApplyDelayWithSpecificEndpoint() {
        delayService.initializeEndpointDelays();
        
        String endpoint = "/test/endpoint";
        long delayTime = 100L; // Short delay for testing
        delayService.setDelayForEndpoint(endpoint, delayTime);
        
        // Measure time taken
        long startTime = System.currentTimeMillis();
        delayService.applyDelay(endpoint);
        long endTime = System.currentTimeMillis();
        
        // Verify delay was applied (allowing some tolerance)
        long actualDelay = endTime - startTime;
        assertTrue(actualDelay >= delayTime - 50, "Delay should be at least close to configured value");
        assertTrue(actualDelay <= delayTime + 100, "Delay should not exceed configured value by much");
    }

    @Test
    void testApplyGlobalDelay() {
        // Given: Use default delay from config
        delayService.initializeEndpointDelays();
        
        long globalDelay = 100L; // Short delay for testing
        delayService.setGlobalDelta(globalDelay);
        
        // Measure time taken
        long startTime = System.currentTimeMillis();
        delayService.applyDelay();
        long endTime = System.currentTimeMillis();
        
        // Verify delay was applied (allowing some tolerance)
        long actualDelay = endTime - startTime;
        assertTrue(actualDelay >= globalDelay - 50, "Delay should be at least close to configured value");
        assertTrue(actualDelay <= globalDelay + 100, "Delay should not exceed configured value by much");
    }
}