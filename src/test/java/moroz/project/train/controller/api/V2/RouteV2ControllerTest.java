package moroz.project.train.controller.api.V2;

import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import moroz.project.train.dto.Route.ResponseRouteDTO;
import moroz.project.train.service.V2.RouteV2Service;
import moroz.project.train.stabs.RouteStab;
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
class RouteV2ControllerTest {
    @MockBean
    private RouteV2Service routeService;

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
        ArrayList<ResponseRouteDTO> list = new ArrayList<>(Arrays.asList(RouteStab.getResponseDto()));
        when(routeService.findAll()).thenReturn(list);

        mvc.perform(get("/api/v2/route/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetById() throws Exception {
        when(routeService.findById(Mockito.any())).thenReturn(RouteStab.getResponseDto());

        mvc.perform(get("/api/v2/route/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void testDeleteById() throws Exception {
        mvc.perform(delete("/api/v2/route/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        when(routeService.create(Mockito.any())).thenReturn(RouteStab.getResponseDto());

        mvc.perform(post("/api/v2/route/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(RouteStab.getRequestDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void testUpdate() throws Exception {
        when(routeService.update(Mockito.any(), Mockito.any())).thenReturn(RouteStab.getResponseDto());

        mvc.perform(put("/api/v2/route/1/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(RouteStab.getRequestDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void testAddStoppageToRoute() throws Exception {
        when(routeService.addStoppageById(Mockito.any(), Mockito.any())).thenReturn(RouteStab.getResponseDto());

        mvc.perform(post("/api/v2/route/1/stoppage/1/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(RouteStab.getRequestDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}