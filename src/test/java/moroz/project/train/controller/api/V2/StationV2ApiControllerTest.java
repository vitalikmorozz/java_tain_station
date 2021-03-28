package moroz.project.train.controller.api.V2;

import javassist.NotFoundException;
import moroz.project.train.dto.Station.RequestStationDTO;
import moroz.project.train.dto.Station.ResponseStationDTO;
import moroz.project.train.entity.Station;
import moroz.project.train.service.V2.StationV2Service;
import moroz.project.train.stabs.StationStab;
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
class StationV2ApiControllerTest {
    private StationV2ApiController stationV2ApiController;
    @Mock
    private StationV2Service stationV2Service;

    @BeforeEach
    void setup() {
        stationV2ApiController = new StationV2ApiController(stationV2Service);
    }

    @Test
    void getAll() {
        Mockito.when(stationV2Service.findAll()).thenReturn(List.of(StationStab.getResponseDto()));
        assertTrue(stationV2Service.findAll().size() > 0);
    }

    @Test
    void getById() {
        Station station = StationStab.getStation();
        try {
            Mockito.when(stationV2Service.findById(Mockito.any())).thenReturn(StationStab.getResponseDto());
            ResponseStationDTO result = stationV2ApiController.getById(StationStab.ID);
            assertAll(
                    () -> assertEquals(station.getId(), result.getId()),
                    () -> assertEquals(station.getName(), result.getName())
            );
            stationV2Service.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void deleteById() {
        try {
            stationV2Service.deleteById(StationStab.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            stationV2Service.create(StationStab.getRequestDto());
            Mockito.verify(stationV2Service, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(RequestStationDTO.class);
        var captorId = ArgumentCaptor.forClass(Long.class);
        RequestStationDTO stationDTO = StationStab.getRequestDto();
        try {
            Mockito.when(stationV2Service.update(Mockito.any(), Mockito.any())).thenReturn(StationStab.getResponseDto());

            ResponseStationDTO result = stationV2ApiController.update(StationStab.ID, StationStab.getRequestDto());
            Mockito.verify(stationV2Service, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());

            assertAll(
                    () -> assertEquals(stationDTO.getName(), result.getName())
            );
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void create() {
        var captor = ArgumentCaptor.forClass(RequestStationDTO.class);
        RequestStationDTO stationDTO = StationStab.getRequestDto();

        Mockito.when(stationV2Service.create(Mockito.any())).thenReturn(StationStab.getResponseDto());
        try {
            ResponseStationDTO result = stationV2ApiController.create(StationStab.getRequestDto());

            Mockito.verify(stationV2Service, Mockito.atLeastOnce()).create(captor.capture());

            assertAll(
                    () -> assertEquals(stationDTO.getName(), result.getName())
            );
        } catch (Exception e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}