package moroz.project.train.controller.api.V2;

import com.fasterxml.jackson.databind.ObjectMapper;
import moroz.project.train.dto.Ticket.ResponseTicketDTO;
import moroz.project.train.service.V2.TicketV2Service;
import moroz.project.train.stabs.TicketStab;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TicketV2ControllerTest {
    @MockBean
    private TicketV2Service ticketService;

    @Autowired
    private MockMvc mvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetAll() throws Exception {
        ArrayList<ResponseTicketDTO> list = new ArrayList<>(Arrays.asList(TicketStab.getResponseDto()));
        when(ticketService.findAll()).thenReturn(list);

        mvc.perform(get("/api/v2/ticket/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().string(containsString(TicketStab.getResponseDto().getPassengerFirstName())));
    }

    @Test
    void testGetById() throws Exception {
        when(ticketService.findById(Mockito.any())).thenReturn(TicketStab.getResponseDto());

        mvc.perform(get("/api/v2/ticket/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(TicketStab.getResponseDto().getPassengerFirstName())));
    }

    @Test
    void testDeleteById() throws Exception {
        mvc.perform(delete("/api/v2/ticket/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        when(ticketService.create(Mockito.any())).thenReturn(TicketStab.getResponseDto());

        mvc.perform(post("/api/v2/ticket/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(TicketStab.getRequestDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(TicketStab.getResponseDto().getPassengerFirstName())));
    }

    @Test
    void testUpdate() throws Exception {
        when(ticketService.update(Mockito.any(), Mockito.any())).thenReturn(TicketStab.getResponseDto());

        mvc.perform(put("/api/v2/ticket/1/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(TicketStab.getRequestDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(TicketStab.getResponseDto().getPassengerFirstName())));
    }
}