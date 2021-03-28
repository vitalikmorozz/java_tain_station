package moroz.project.train.controller.api.V2;

import javassist.NotFoundException;
import moroz.project.train.dto.Ticket.RequestTicketDTO;
import moroz.project.train.dto.Ticket.ResponseTicketDTO;
import moroz.project.train.entity.Ticket;
import moroz.project.train.service.V2.TicketV2Service;
import moroz.project.train.stabs.TicketStab;
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
class TicketV2ControllerTest {
    private TicketV2Controller TicketV2ApiController;
    @Mock
    private TicketV2Service TicketV2Service;

    @BeforeEach
    void setup() {
        TicketV2ApiController = new TicketV2Controller(TicketV2Service);
    }

    @Test
    void getAll() {
        Mockito.when(TicketV2Service.findAll()).thenReturn(List.of(TicketStab.getResponseDto()));
        assertTrue(TicketV2Service.findAll().size() > 0);
    }

    @Test
    void getById() {
        Ticket ticket = TicketStab.getTicket();
        try {
            Mockito.when(TicketV2Service.findById(Mockito.any())).thenReturn(TicketStab.getResponseDto());
            ResponseTicketDTO result = TicketV2ApiController.getById(TicketStab.ID);
            assertAll(
                    () -> assertEquals(ticket.getId(), result.getId()),
                    () -> assertEquals(ticket.getPassengerFirstName(), result.getPassengerFirstName()),
                    () -> assertEquals(ticket.getPassengerLastName(), result.getPassengerLastName())
            );
            TicketV2Service.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void deleteById() {
        try {
            TicketV2Service.deleteById(TicketStab.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            TicketV2Service.create(TicketStab.getRequestDto());
            Mockito.verify(TicketV2Service, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(RequestTicketDTO.class);
        var captorId = ArgumentCaptor.forClass(Long.class);
        RequestTicketDTO ticketDTO = TicketStab.getRequestDto();
        try {
            Mockito.when(TicketV2Service.update(Mockito.any(), Mockito.any())).thenReturn(TicketStab.getResponseDto());

            ResponseTicketDTO result = TicketV2ApiController.update(TicketStab.ID, TicketStab.getRequestDto());
            Mockito.verify(TicketV2Service, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());

            assertAll(
                    () -> assertEquals(ticketDTO.getRouteId(), result.getRoute().getId()),
                    () -> assertEquals(ticketDTO.getPassengerFirstName(), result.getPassengerFirstName()),
                    () -> assertEquals(ticketDTO.getPassengerLastName(), result.getPassengerLastName())
            );
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void create() {
        var captor = ArgumentCaptor.forClass(RequestTicketDTO.class);
        RequestTicketDTO ticketDTO = TicketStab.getRequestDto();

        try {
            Mockito.when(TicketV2Service.create(Mockito.any())).thenReturn(TicketStab.getResponseDto());
            ResponseTicketDTO result = TicketV2ApiController.create(TicketStab.getRequestDto());

            Mockito.verify(TicketV2Service, Mockito.atLeastOnce()).create(captor.capture());

            assertAll(
                    () -> assertEquals(ticketDTO.getRouteId(), result.getRoute().getId()),
                    () -> assertEquals(ticketDTO.getPassengerFirstName(), result.getPassengerFirstName()),
                    () -> assertEquals(ticketDTO.getPassengerLastName(), result.getPassengerLastName())
            );
        } catch (Exception e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}