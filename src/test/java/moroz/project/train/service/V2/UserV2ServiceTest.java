package moroz.project.train.service.V2;

import javassist.NotFoundException;
import moroz.project.train.dto.User.RequestUserDTO;
import moroz.project.train.dto.User.ResponseUserDTO;
import moroz.project.train.entity.User;
import moroz.project.train.repository.UserRepository;
import moroz.project.train.stabs.UserStab;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class UserV2ServiceTest {
    private UserV2Service userV2Service;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userV2Service = new UserV2Service(userRepository, new ModelMapper());
    }

    @Test
    void findAll() {
        User user = UserStab.getUser();

        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        assertTrue(userV2Service.findAll().size() > 0);
    }

    @Test
    void findById() {
        User user = UserStab.getUser();

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

        try {
            ResponseUserDTO result = userV2Service.findById(UserStab.ID);
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
    void update() {
        var captor = ArgumentCaptor.forClass(User.class);
        RequestUserDTO userDTO = UserStab.getRequestDto();
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(UserStab.getUser());
        Mockito.when(userRepository.existsById(UserStab.ID)).thenReturn(true);
        try {
            ResponseUserDTO saved = userV2Service.create(UserStab.getRequestDto());
            ResponseUserDTO result = userV2Service.update(UserStab.ID, UserStab.getRequestDto());
            Mockito.verify(userRepository, Mockito.atLeastOnce()).save(captor.capture());

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
        var captor = ArgumentCaptor.forClass(User.class);
        RequestUserDTO userDTO = UserStab.getRequestDto();

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(UserStab.getUser());

        ResponseUserDTO result = userV2Service.create(UserStab.getRequestDto());

        Mockito.verify(userRepository, Mockito.atLeastOnce()).save(captor.capture());

        assertAll(
                () -> assertEquals(userDTO.getFirstName(), result.getFirstName()),
                () -> assertEquals(userDTO.getLastName(), result.getLastName()),
                () -> assertEquals(userDTO.getEmail(), result.getEmail()),
                () -> assertEquals(userDTO.getPassword(), result.getPassword())
        );
    }

    @Test
    void deleteById() {
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(UserStab.getUser());
        Mockito.when(userRepository.existsById(UserStab.ID)).thenReturn(true);
        try {
            userV2Service.deleteById(UserStab.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            userV2Service.create(UserStab.getRequestDto());
            Mockito.verify(userRepository, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}