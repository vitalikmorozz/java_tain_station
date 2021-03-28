package moroz.project.train.service.V2;

import javassist.NotFoundException;
import jdk.jfr.Category;
import moroz.project.train.dto.Stoppage.RequestStoppageDTO;
import moroz.project.train.dto.Stoppage.ResponseStoppageDTO;
import moroz.project.train.dto.Stoppage.ResponseStoppageDTO;
import moroz.project.train.entity.Stoppage;
import moroz.project.train.repository.StationRepository;
import moroz.project.train.repository.StoppageRepository;
import moroz.project.train.stabs.StationStab;
import moroz.project.train.stabs.StoppageStab;
import moroz.project.train.stabs.StoppageStab;
import moroz.project.train.stabs.StoppageStab;
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
class StoppageV2ServiceTest {
    private StoppageV2Service stoppageV2Service;
    @Mock
    private StoppageRepository stoppageRepository;
    @Mock
    private StationRepository stationRepository;

    @BeforeEach
    void setup() {
        stoppageV2Service = new StoppageV2Service(stoppageRepository, stationRepository, new ModelMapper());
    }

    @Test
    void findAll() {
        Stoppage stoppage = StoppageStab.getStoppage();

        Mockito.when(stoppageRepository.findAll()).thenReturn(List.of(stoppage));

        assertTrue(stoppageV2Service.findAll().size() > 0);
    }

    @Test
    void findById() {
        Stoppage stoppage = StoppageStab.getStoppage();

        Mockito.when(stoppageRepository.findById(Mockito.any())).thenReturn(Optional.of(stoppage));

        try {
            ResponseStoppageDTO result = stoppageV2Service.findById(StoppageStab.ID);
            assertAll(
                    () -> assertEquals(stoppage.getId(), result.getId()),
                    () -> assertEquals(stoppage.getStoppageOrder(), result.getStoppageOrder()),
                    () -> assertEquals(stoppage.getArrivalTime(), result.getArrivalTime()),
                    () -> assertEquals(stoppage.getDepartureTime(), result.getDepartureTime())
            );
            stoppageV2Service.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(Stoppage.class);
        RequestStoppageDTO stoppageDTO = StoppageStab.getRequestDto();
        Mockito.when(stoppageRepository.save(Mockito.any())).thenReturn(StoppageStab.getStoppage());
        Mockito.when(stationRepository.findById(Mockito.any())).thenReturn(Optional.of(StationStab.getStation()));
        Mockito.when(stoppageRepository.existsById(StoppageStab.ID)).thenReturn(true);
        try {
            ResponseStoppageDTO saved = stoppageV2Service.create(StoppageStab.getRequestDto());

            ResponseStoppageDTO result = stoppageV2Service.update(StoppageStab.ID, StoppageStab.getRequestDto());
            Mockito.verify(stoppageRepository, Mockito.atLeastOnce()).save(captor.capture());

            assertAll(
                    () -> assertEquals(stoppageDTO.getStoppageOrder(), result.getStoppageOrder()),
                    () -> assertEquals(stoppageDTO.getArrivalTime(), result.getArrivalTime()),
                    () -> assertEquals(stoppageDTO.getDepartureTime(), result.getDepartureTime())
            );
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void create() {
        var captor = ArgumentCaptor.forClass(Stoppage.class);
        RequestStoppageDTO stoppageDTO = StoppageStab.getRequestDto();

        try {
            ResponseStoppageDTO result = stoppageV2Service.create(StoppageStab.getRequestDto());

            Mockito.verify(stoppageRepository, Mockito.atLeastOnce()).save(captor.capture());

            assertAll(
                    () -> assertEquals(stoppageDTO.getStoppageOrder(), result.getStoppageOrder()),
                    () -> assertEquals(stoppageDTO.getArrivalTime(), result.getArrivalTime()),
                    () -> assertEquals(stoppageDTO.getDepartureTime(), result.getDepartureTime())
            );
        } catch (Exception e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }

    }

    @Test
    void deleteById() {
        Mockito.when(stoppageRepository.save(Mockito.any())).thenReturn(StoppageStab.getStoppage());
        Mockito.when(stationRepository.findById(Mockito.any())).thenReturn(Optional.of(StationStab.getStation()));
        Mockito.when(stoppageRepository.existsById(StoppageStab.ID)).thenReturn(true);
        try {
            stoppageV2Service.deleteById(StoppageStab.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            stoppageV2Service.create(StoppageStab.getRequestDto());
            Mockito.verify(stoppageRepository, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}