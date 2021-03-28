package moroz.project.train.controller.api.V2;

import javassist.NotFoundException;
import moroz.project.train.dto.Stoppage.RequestStoppageDTO;
import moroz.project.train.dto.Stoppage.ResponseStoppageDTO;
import moroz.project.train.entity.Stoppage;
import moroz.project.train.service.V2.StoppageV2Service;
import moroz.project.train.stabs.StoppageStab;
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
class StoppageV2ControllerTest {
    private StoppageV2Controller stoppageV2ApiController;
    @Mock
    private StoppageV2Service stoppageV2Service;

    @BeforeEach
    void setup() {
        stoppageV2ApiController = new StoppageV2Controller(stoppageV2Service);
    }

    @Test
    void getAll() {
        Mockito.when(stoppageV2Service.findAll()).thenReturn(List.of(StoppageStab.getResponseDto()));
        assertTrue(stoppageV2Service.findAll().size() > 0);
    }

    @Test
    void getById() {
        Stoppage stoppage = StoppageStab.getStoppage();
        try {
            Mockito.when(stoppageV2Service.findById(Mockito.any())).thenReturn(StoppageStab.getResponseDto());
            ResponseStoppageDTO result = stoppageV2ApiController.getById(StoppageStab.ID);
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
    void deleteById() {
        try {
            stoppageV2Service.deleteById(StoppageStab.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            stoppageV2Service.create(StoppageStab.getRequestDto());
            Mockito.verify(stoppageV2Service, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(RequestStoppageDTO.class);
        var captorId = ArgumentCaptor.forClass(Long.class);
        RequestStoppageDTO stoppageDTO = StoppageStab.getRequestDto();
        try {
            Mockito.when(stoppageV2Service.update(Mockito.any(), Mockito.any())).thenReturn(StoppageStab.getResponseDto());

            ResponseStoppageDTO result = stoppageV2ApiController.update(StoppageStab.ID, StoppageStab.getRequestDto());
            Mockito.verify(stoppageV2Service, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());

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
        var captor = ArgumentCaptor.forClass(RequestStoppageDTO.class);
        RequestStoppageDTO stoppageDTO = StoppageStab.getRequestDto();

        try {
            Mockito.when(stoppageV2Service.create(Mockito.any())).thenReturn(StoppageStab.getResponseDto());
            ResponseStoppageDTO result = stoppageV2ApiController.create(StoppageStab.getRequestDto());

            Mockito.verify(stoppageV2Service, Mockito.atLeastOnce()).create(captor.capture());

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
}