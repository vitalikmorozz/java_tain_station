package moroz.project.train.controller.api.V2;

import javassist.NotFoundException;
import moroz.project.train.dto.Route.RequestRouteDTO;
import moroz.project.train.dto.Route.ResponseRouteDTO;
import moroz.project.train.entity.Route;
import moroz.project.train.service.V2.RouteV2Service;
import moroz.project.train.stabs.RouteStab;
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
class RouteV2ControllerTest {
    private RouteV2Controller RouteV2ApiController;
    @Mock
    private RouteV2Service RouteV2Service;

    @BeforeEach
    void setup() {
        RouteV2ApiController = new RouteV2Controller(RouteV2Service);
    }

    @Test
    void getAll() {
        Mockito.when(RouteV2Service.findAll()).thenReturn(List.of(RouteStab.getResponseDto()));
        assertTrue(RouteV2Service.findAll().size() > 0);
    }

    @Test
    void getById() {
        Route route = RouteStab.getRoute();
        try {
            Mockito.when(RouteV2Service.findById(Mockito.any())).thenReturn(RouteStab.getResponseDto());
            ResponseRouteDTO result = RouteV2ApiController.getById(RouteStab.ID);
            assertAll(
                    () -> assertEquals(route.getId(), result.getId())
            );
            RouteV2Service.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void deleteById() {
        try {
            RouteV2Service.deleteById(RouteStab.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            RouteV2Service.create(RouteStab.getRequestDto());
            Mockito.verify(RouteV2Service, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(RequestRouteDTO.class);
        var captorId = ArgumentCaptor.forClass(Long.class);
        RequestRouteDTO routeDTO = RouteStab.getRequestDto();
        try {
            Mockito.when(RouteV2Service.update(Mockito.any(), Mockito.any())).thenReturn(RouteStab.getResponseDto());

            ResponseRouteDTO result = RouteV2ApiController.update(RouteStab.ID, RouteStab.getRequestDto());
            Mockito.verify(RouteV2Service, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());

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
        var captor = ArgumentCaptor.forClass(RequestRouteDTO.class);
        RequestRouteDTO routeDTO = RouteStab.getRequestDto();

        try {
            Mockito.when(RouteV2Service.create(Mockito.any())).thenReturn(RouteStab.getResponseDto());
            ResponseRouteDTO result = RouteV2ApiController.create(RouteStab.getRequestDto());

            Mockito.verify(RouteV2Service, Mockito.atLeastOnce()).create(captor.capture());

            assertAll(
                    () -> assertEquals(routeDTO.getTrainId(), result.getTrain().getId())
            );
        } catch (Exception e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}