package moroz.project.train.controller.api.V2;

import javassist.NotFoundException;
import moroz.project.train.dto.User.RequestUserDTO;
import moroz.project.train.dto.User.ResponseUserDTO;
import moroz.project.train.entity.User;
import moroz.project.train.service.V2.UserV2Service;
import moroz.project.train.stabs.UserStab;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class UserV2ApiControllerTest {
    private UserV2ApiController userV2ApiController;
    @Mock
    private UserV2Service userV2Service;

    @BeforeEach
    void setup() {
        userV2ApiController = new UserV2ApiController(userV2Service);
    }

    @Test
    void getAll() {
        Mockito.when(userV2Service.findAll()).thenReturn(List.of(UserStab.getResponseDto()));
        assertTrue(userV2Service.findAll().size() > 0);
    }

    @Test
    void getById() {
        User user = UserStab.getUser();
        try {
            Mockito.when(userV2Service.findById(Mockito.any())).thenReturn(UserStab.getResponseDto());
            ResponseUserDTO result = userV2ApiController.getById(UserStab.ID);
            assertAll(
                    () -> assertEquals(user.getId(), result.getId()),
                    () -> assertEquals(user.getFirstName(), result.getFirstName()),
                    () -> assertEquals(user.getLastName(), result.getLastName()),
                    () -> assertEquals(user.getEmail(), result.getEmail()),
                    () -> assertEquals(user.getPassword(), result.getPassword())
            );
            userV2Service.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void deleteById() {
        try {
            userV2Service.deleteById(UserStab.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            userV2Service.create(UserStab.getRequestDto());
            Mockito.verify(userV2Service, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(RequestUserDTO.class);
        var captorId = ArgumentCaptor.forClass(Long.class);
        RequestUserDTO userDTO = UserStab.getRequestDto();
        try {
            Mockito.when(userV2Service.update(Mockito.any(), Mockito.any())).thenReturn(UserStab.getResponseDto());

            ResponseUserDTO result = userV2ApiController.update(UserStab.ID, UserStab.getRequestDto());
            Mockito.verify(userV2Service, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());

            assertAll(
                    () -> assertEquals(userDTO.getFirstName(), result.getFirstName()),
                    () -> assertEquals(userDTO.getLastName(), result.getLastName()),
                    () -> assertEquals(userDTO.getEmail(), result.getEmail()),
                    () -> assertEquals(userDTO.getPassword(), result.getPassword())
            );
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void create() {
        var captor = ArgumentCaptor.forClass(RequestUserDTO.class);
        RequestUserDTO userDTO = UserStab.getRequestDto();

        Mockito.when(userV2Service.create(Mockito.any())).thenReturn(UserStab.getResponseDto());
        try {
            ResponseUserDTO result = userV2ApiController.create(UserStab.getRequestDto());

            Mockito.verify(userV2Service, Mockito.atLeastOnce()).create(captor.capture());

            assertAll(
                    () -> assertEquals(userDTO.getFirstName(), result.getFirstName()),
                    () -> assertEquals(userDTO.getLastName(), result.getLastName()),
                    () -> assertEquals(userDTO.getEmail(), result.getEmail()),
                    () -> assertEquals(userDTO.getPassword(), result.getPassword())
            );
        } catch (Exception e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}