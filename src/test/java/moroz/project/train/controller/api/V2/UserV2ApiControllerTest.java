package moroz.project.train.controller.api.V2;

import com.fasterxml.jackson.databind.ObjectMapper;
import moroz.project.train.dto.User.ResponseUserDTO;
import moroz.project.train.service.V2.UserV2Service;
import moroz.project.train.stabs.UserStab;
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
class UserV2ApiControllerTest {
    @MockBean
    private UserV2Service userService;

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
        ArrayList<ResponseUserDTO> list = new ArrayList<>(Arrays.asList(UserStab.getResponseDto()));
        when(userService.findAll()).thenReturn(list);

        mvc.perform(get("/api/v2/users/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().string(containsString(UserStab.getResponseDto().getFirstName())));
    }

    @Test
    void testGetById() throws Exception {
        when(userService.findById(Mockito.any())).thenReturn(UserStab.getResponseDto());

        mvc.perform(get("/api/v2/users/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(UserStab.getResponseDto().getFirstName())));
    }

    @Test
    void testDeleteById() throws Exception {
        mvc.perform(delete("/api/v2/users/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        when(userService.create(Mockito.any())).thenReturn(UserStab.getResponseDto());

        mvc.perform(post("/api/v2/users/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(UserStab.getRequestDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(UserStab.getResponseDto().getFirstName())));
    }

    @Test
    void testUpdate() throws Exception {
        when(userService.update(Mockito.any(), Mockito.any())).thenReturn(UserStab.getResponseDto());

        mvc.perform(put("/api/v2/users/1/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(UserStab.getRequestDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(UserStab.getResponseDto().getFirstName())));
    }
}