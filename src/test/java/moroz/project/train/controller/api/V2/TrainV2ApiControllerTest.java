package moroz.project.train.controller.api.V2;

import javassist.NotFoundException;
import moroz.project.train.dto.Train.RequestTrainDTO;
import moroz.project.train.dto.Train.ResponseTrainDTO;
import moroz.project.train.entity.Train;
import moroz.project.train.service.V2.TrainV2Service;
import moroz.project.train.stabs.TrainStab;
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
class TrainV2ApiControllerTest {
    private TrainV2ApiController trainV2ApiController;
    @Mock
    private TrainV2Service trainV2Service;

    @BeforeEach
    void setup() {
        trainV2ApiController = new TrainV2ApiController(trainV2Service);
    }

    @Test
    void getAll() {
        Mockito.when(trainV2Service.findAll()).thenReturn(List.of(TrainStab.getResponseDto()));
        assertTrue(trainV2Service.findAll().size() > 0);
    }

    @Test
    void getById() {
        Train train = TrainStab.getTrain();
        try {
            Mockito.when(trainV2Service.findById(Mockito.any())).thenReturn(TrainStab.getResponseDto());
            ResponseTrainDTO result = trainV2ApiController.getById(TrainStab.ID);
            assertAll(
                    () -> assertEquals(train.getId(), result.getId()),
                    () -> assertEquals(train.getDescription(), result.getDescription()),
                    () -> assertEquals(train.getNumber(), result.getNumber()),
                    () -> assertEquals(train.getSeatsCount(), result.getSeatsCount())
            );
            trainV2Service.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void deleteById() {
        try {
            trainV2Service.deleteById(TrainStab.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            trainV2Service.create(TrainStab.getRequestDto());
            Mockito.verify(trainV2Service, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(RequestTrainDTO.class);
        var captorId = ArgumentCaptor.forClass(Long.class);
        RequestTrainDTO trainDTO = TrainStab.getRequestDto();
        try {
            Mockito.when(trainV2Service.update(Mockito.any(), Mockito.any())).thenReturn(TrainStab.getResponseDto());

            ResponseTrainDTO result = trainV2ApiController.update(TrainStab.ID, TrainStab.getRequestDto());
            Mockito.verify(trainV2Service, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());

            assertAll(
                    () -> assertEquals(trainDTO.getDescription(), result.getDescription()),
                    () -> assertEquals(trainDTO.getNumber(), result.getNumber()),
                    () -> assertEquals(trainDTO.getSeatsCount(), result.getSeatsCount())
            );
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void create() {
        var captor = ArgumentCaptor.forClass(RequestTrainDTO.class);
        RequestTrainDTO trainDTO = TrainStab.getRequestDto();

        Mockito.when(trainV2Service.create(Mockito.any())).thenReturn(TrainStab.getResponseDto());
        try {
            ResponseTrainDTO result = trainV2ApiController.create(TrainStab.getRequestDto());

            Mockito.verify(trainV2Service, Mockito.atLeastOnce()).create(captor.capture());

            assertAll(
                    () -> assertEquals(trainDTO.getDescription(), result.getDescription()),
                    () -> assertEquals(trainDTO.getNumber(), result.getNumber()),
                    () -> assertEquals(trainDTO.getSeatsCount(), result.getSeatsCount())
            );
        } catch (Exception e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}