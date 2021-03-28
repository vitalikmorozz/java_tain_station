package moroz.project.train.service.V2;

import javassist.NotFoundException;
import jdk.jfr.Category;
import moroz.project.train.dto.Station.RequestStationDTO;
import moroz.project.train.dto.Station.ResponseStationDTO;
import moroz.project.train.dto.Station.ResponseStationDTO;
import moroz.project.train.entity.Station;
import moroz.project.train.repository.StationRepository;
import moroz.project.train.stabs.StationStab;
import moroz.project.train.stabs.StationStab;
import moroz.project.train.stabs.StationStab;
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
class StationV2ServiceTest {
    private StationV2Service stationV2Service;
    @Mock
    private StationRepository stationRepository;

    @BeforeEach
    void setup() {
        stationV2Service = new StationV2Service(stationRepository, new ModelMapper());
    }

    @Test
    void findAll() {
        Station station = StationStab.getStation();

        Mockito.when(stationRepository.findAll()).thenReturn(List.of(station));

        assertTrue(stationV2Service.findAll().size() > 0);
    }

    @Test
    void findById() {
        Station station = StationStab.getStation();

        Mockito.when(stationRepository.findById(Mockito.any())).thenReturn(Optional.of(station));

        try {
            ResponseStationDTO result = stationV2Service.findById(StationStab.ID);
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
    void update() {
        var captor = ArgumentCaptor.forClass(Station.class);
        RequestStationDTO stationDTO = StationStab.getRequestDto();
        Mockito.when(stationRepository.save(Mockito.any())).thenReturn(StationStab.getStation());
        Mockito.when(stationRepository.existsById(StationStab.ID)).thenReturn(true);
        try {
            ResponseStationDTO saved = stationV2Service.create(StationStab.getRequestDto());
            ResponseStationDTO result = stationV2Service.update(StationStab.ID, StationStab.getRequestDto());
            Mockito.verify(stationRepository, Mockito.atLeastOnce()).save(captor.capture());

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
        var captor = ArgumentCaptor.forClass(Station.class);
        RequestStationDTO stationDTO = StationStab.getRequestDto();

        Mockito.when(stationRepository.save(Mockito.any())).thenReturn(StationStab.getStation());

        ResponseStationDTO result = stationV2Service.create(StationStab.getRequestDto());

        Mockito.verify(stationRepository, Mockito.atLeastOnce()).save(captor.capture());

         assertAll(
                 () -> assertEquals(stationDTO.getName(), result.getName())
         );
    }

    @Test
    void deleteById() {
        Mockito.when(stationRepository.save(Mockito.any())).thenReturn(StationStab.getStation());
        Mockito.when(stationRepository.existsById(StationStab.ID)).thenReturn(true);
        try {
            stationV2Service.deleteById(StationStab.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            stationV2Service.create(StationStab.getRequestDto());
            Mockito.verify(stationRepository, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}