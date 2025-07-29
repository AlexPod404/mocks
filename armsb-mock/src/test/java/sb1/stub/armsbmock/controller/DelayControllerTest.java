package sb1.stub.armsbmock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sb1.stub.armsbmock.service.DelayService;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DelayController.class)
class DelayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DelayService delayService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllDelays() throws Exception {
        // Given
        Map<String, Long> mockDelays = new HashMap<>();
        mockDelays.put("/clients/srvgetclientlist", 250L);
        mockDelays.put("/cti/getCommunications", 400L);
        when(delayService.getAllEndpointDelays()).thenReturn(mockDelays);

        // When & Then
        mockMvc.perform(get("/api/delays"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['/clients/srvgetclientlist']").value(250))
                .andExpect(jsonPath("$['/cti/getCommunications']").value(400));

        verify(delayService).getAllEndpointDelays();
    }

    @Test
    void testGetDelayForSpecificEndpoint() throws Exception {
        // Given
        String endpoint = "/clients/srvgetclientlist";
        Long expectedDelay = 250L;
        when(delayService.getDelayForEndpoint(endpoint, true)).thenReturn(expectedDelay);

        // When & Then
        mockMvc.perform(get("/api/delays/endpoint")
                        .param("endpoint", endpoint))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("250"));

        verify(delayService).getDelayForEndpoint(endpoint, true);
    }

    @Test
    void testGetDelayForEndpointByPath() throws Exception {
        // Given
        String endpoint = "/clients/srvgetclientlist";
        String encodedEndpoint = "%2Fclients%2Fsrvgetclientlist";
        Long expectedDelay = 250L;
        when(delayService.getDelayForEndpoint(endpoint, true)).thenReturn(expectedDelay);

        // When & Then
        mockMvc.perform(get("/api/delays/" + encodedEndpoint))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("250"));

        verify(delayService).getDelayForEndpoint(endpoint, true);
    }

    @Test
    void testSetDelayForEndpoint() throws Exception {
        // Given
        String endpoint = "/clients/srvgetclientlist";
        long delay = 500L;

        // When & Then
        mockMvc.perform(post("/api/delays")
                        .param("endpoint", endpoint)
                        .param("delay", String.valueOf(delay)))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully set delay for endpoint '/clients/srvgetclientlist' to 500 ms"));

        verify(delayService).setDelayForEndpoint(endpoint, delay);
    }

    @Test
    void testUpdateDelayForEndpoint() throws Exception {
        // Given
        String endpoint = "/clients/srvgetclientlist";
        long delay = 600L;

        // When & Then
        mockMvc.perform(put("/api/delays")
                        .param("endpoint", endpoint)
                        .param("delay", String.valueOf(delay)))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully updated delay for endpoint '/clients/srvgetclientlist' to 600 ms"));

        verify(delayService).setDelayForEndpoint(endpoint, delay);
    }

    @Test
    void testRemoveDelayForEndpoint() throws Exception {
        // Given
        String endpoint = "/clients/srvgetclientlist";

        // When & Then
        mockMvc.perform(delete("/api/delays")
                        .param("endpoint", endpoint))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully removed delay configuration for endpoint: /clients/srvgetclientlist"));

        verify(delayService).removeDelayForEndpoint(endpoint);
    }

    @Test
    void testSetGlobalDelay() throws Exception {
        // Given
        long globalDelay = 1000L;

        // When & Then
        mockMvc.perform(post("/api/delays/global")
                        .param("delay", String.valueOf(globalDelay)))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully set global delay to 1000 ms"));

        verify(delayService).setGlobalDelta(globalDelay);
    }

    @Test
    void testGetGlobalDelay() throws Exception {
        // Given
        long expectedGlobalDelay = 500L;
        when(delayService.getGlobalDelta()).thenReturn(expectedGlobalDelay);

        // When & Then
        mockMvc.perform(get("/api/delays/global"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("500"));

        verify(delayService).getGlobalDelta();
    }
}