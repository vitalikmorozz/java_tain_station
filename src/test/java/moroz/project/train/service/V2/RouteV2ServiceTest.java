package moroz.project.train.service.V2;

import javassist.NotFoundException;
import jdk.jfr.Category;
import moroz.project.train.dto.Route.RequestRouteDTO;
import moroz.project.train.dto.Route.ResponseRouteDTO;
import moroz.project.train.dto.Ticket.ResponseTicketDTO;
import moroz.project.train.entity.Route;
import moroz.project.train.entity.Stoppage;
import moroz.project.train.repository.*;
import moroz.project.train.stabs.RouteStab;
import moroz.project.train.stabs.StoppageStab;
import moroz.project.train.stabs.TicketStab;
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
class RouteV2ServiceTest {
    private RouteV2Service routeV2Service;
    @Mock
    private RouteRepository routeRepository;
    @Mock
    private TrainRepository trainRepository;
    @Mock
    private StoppageRepository stoppageRepository;

    @BeforeEach
    void setup() {
        routeV2Service = new RouteV2Service(routeRepository, trainRepository, stoppageRepository, new ModelMapper());
    }

    @Test
    void findAll() {
        Route route = RouteStab.getRoute();

        Mockito.when(routeRepository.findAll()).thenReturn(List.of(route));

        assertTrue(routeV2Service.findAll().size() > 0);
    }

    @Test
    void findById() {
        Route route = RouteStab.getRoute();

        Mockito.when(routeRepository.findById(Mockito.any())).thenReturn(Optional.of(route));

        try {
            ResponseRouteDTO result = routeV2Service.findById(RouteStab.ID);
            assertAll(
                    () -> assertEquals(route.getId(), result.getId())
            );
            routeV2Service.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(Route.class);
        RequestRouteDTO routeDTO = RouteStab.getRequestDto();
        Mockito.when(routeRepository.save(Mockito.any())).thenReturn(RouteStab.getRoute());
        Mockito.when(routeRepository.existsById(RouteStab.ID)).thenReturn(true);
        Mockito.when(trainRepository.findById(Mockito.any())).thenReturn(Optional.of(TrainStab.getTrain()));
        try {
            ResponseRouteDTO saved = routeV2Service.create(RouteStab.getRequestDto());

            ResponseRouteDTO result = routeV2Service.update(RouteStab.ID, RouteStab.getRequestDto());
            Mockito.verify(routeRepository, Mockito.atLeastOnce()).save(captor.capture());

            assertAll(
                    () -> assertEquals(routeDTO.getTrainId(), result.getTrain().getId())
            );
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void create() {
        var captor = ArgumentCaptor.forClass(Route.class);
        RequestRouteDTO routeDTO = RouteStab.getRequestDto();

        try {
            ResponseRouteDTO result = routeV2Service.create(RouteStab.getRequestDto());

            Mockito.verify(routeRepository, Mockito.atLeastOnce()).save(captor.capture());

            assertAll(
                    () -> assertEquals(routeDTO.getTrainId(), result.getTrain().getId())
            );
        } catch (Exception e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }

    }

    @Test
    void deleteById() {
        Mockito.when(routeRepository.save(Mockito.any())).thenReturn(RouteStab.getRoute());
        Mockito.when(routeRepository.existsById(RouteStab.ID)).thenReturn(true);
        Mockito.when(trainRepository.findById(Mockito.any())).thenReturn(Optional.of(TrainStab.getTrain()));
        try {
            routeV2Service.deleteById(RouteStab.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            routeV2Service.create(RouteStab.getRequestDto());
            Mockito.verify(routeRepository, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void addStoppageById() {
        var captor = ArgumentCaptor.forClass(Stoppage.class);

        Mockito.when(routeRepository.existsById(RouteStab.ID)).thenReturn(true);
        Mockito.when(stoppageRepository.existsById(StoppageStab.ID)).thenReturn(true);
        Mockito.when(routeRepository.getOne(Mockito.any())).thenReturn(RouteStab.getRoute());
        Mockito.when(stoppageRepository.getOne(Mockito.any())).thenReturn(StoppageStab.getStoppage());
        try {
            routeV2Service.addStoppageById(RouteStab.ID, StoppageStab.ID);
        } catch (Exception e) {
            String expectedMessage = "already have";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
        Mockito.verify(stoppageRepository, Mockito.atLeastOnce()).save(captor.capture());
    }
}