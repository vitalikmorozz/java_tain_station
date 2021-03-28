package moroz.project.train.service.V2;

import javassist.NotFoundException;
import moroz.project.train.dto.Train.RequestTrainDTO;
import moroz.project.train.dto.Train.ResponseTrainDTO;
import moroz.project.train.entity.Train;
import moroz.project.train.repository.TrainRepository;
import moroz.project.train.stabs.TrainStab;
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
class TrainV2ServiceTest {
    private TrainV2Service trainV2Service;
    @Mock
    private TrainRepository trainRepository;

    @BeforeEach
    void setup() {
        trainV2Service = new TrainV2Service(trainRepository, new ModelMapper());
    }

    @Test
    void findAll() {
        Train train = TrainStab.getTrain();

        Mockito.when(trainRepository.findAll()).thenReturn(List.of(train));

        assertTrue(trainV2Service.findAll().size() > 0);
    }

    @Test
    void findById() {
        Train train = TrainStab.getTrain();

        Mockito.when(trainRepository.findById(Mockito.any())).thenReturn(Optional.of(train));

        try {
            ResponseTrainDTO result = trainV2Service.findById(TrainStab.ID);
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
    void update() {
        var captor = ArgumentCaptor.forClass(Train.class);
        RequestTrainDTO trainDTO = TrainStab.getRequestDto();
        Mockito.when(trainRepository.save(Mockito.any())).thenReturn(TrainStab.getTrain());
        Mockito.when(trainRepository.existsById(TrainStab.ID)).thenReturn(true);
        try {
            ResponseTrainDTO saved = trainV2Service.create(TrainStab.getRequestDto());
            ResponseTrainDTO result = trainV2Service.update(TrainStab.ID, TrainStab.getRequestDto());
            Mockito.verify(trainRepository, Mockito.atLeastOnce()).save(captor.capture());

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
        var captor = ArgumentCaptor.forClass(Train.class);
        RequestTrainDTO trainDTO = TrainStab.getRequestDto();

        Mockito.when(trainRepository.save(Mockito.any())).thenReturn(TrainStab.getTrain());

        ResponseTrainDTO result = trainV2Service.create(TrainStab.getRequestDto());

        Mockito.verify(trainRepository, Mockito.atLeastOnce()).save(captor.capture());

        assertAll(
                () -> assertEquals(trainDTO.getDescription(), result.getDescription()),
                () -> assertEquals(trainDTO.getNumber(), result.getNumber()),
                () -> assertEquals(trainDTO.getSeatsCount(), result.getSeatsCount())
        );
    }

    @Test
    void deleteById() {
        Mockito.when(trainRepository.save(Mockito.any())).thenReturn(TrainStab.getTrain());
        Mockito.when(trainRepository.existsById(TrainStab.ID)).thenReturn(true);
        try {
            trainV2Service.deleteById(TrainStab.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            trainV2Service.create(TrainStab.getRequestDto());
            Mockito.verify(trainRepository, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}