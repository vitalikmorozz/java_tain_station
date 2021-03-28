package moroz.project.train.service.V2;

import javassist.NotFoundException;
import moroz.project.train.dto.Stoppage.ResponseStoppageDTO;
import moroz.project.train.dto.Ticket.RequestTicketDTO;
import moroz.project.train.dto.Ticket.ResponseTicketDTO;
import moroz.project.train.entity.Ticket;
import moroz.project.train.repository.RouteRepository;
import moroz.project.train.repository.TicketRepository;
import moroz.project.train.stabs.RouteStab;
import moroz.project.train.stabs.StationStab;
import moroz.project.train.stabs.StoppageStab;
import moroz.project.train.stabs.TicketStab;
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
class TicketV2ServiceTest {
    private TicketV2Service ticketV2Service;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private RouteRepository routeRepository;

    @BeforeEach
    void setup() {
        ticketV2Service = new TicketV2Service(ticketRepository, routeRepository, new ModelMapper());
    }

    @Test
    void findAll() {
        Ticket ticket = TicketStab.getTicket();

        Mockito.when(ticketRepository.findAll()).thenReturn(List.of(ticket));

        assertTrue(ticketV2Service.findAll().size() > 0);
    }

    @Test
    void findById() {
        Ticket ticket = TicketStab.getTicket();

        Mockito.when(ticketRepository.findById(Mockito.any())).thenReturn(Optional.of(ticket));

        try {
            ResponseTicketDTO result = ticketV2Service.findById(TicketStab.ID);
            assertAll(
                    () -> assertEquals(ticket.getId(), result.getId()),
                    () -> assertEquals(ticket.getPassengerFirstName(), result.getPassengerFirstName()),
                    () -> assertEquals(ticket.getPassengerLastName(), result.getPassengerLastName())
            );
            ticketV2Service.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(Ticket.class);
        RequestTicketDTO ticketDTO = TicketStab.getRequestDto();
        Mockito.when(ticketRepository.save(Mockito.any())).thenReturn(TicketStab.getTicket());
        Mockito.when(routeRepository.findById(Mockito.any())).thenReturn(Optional.of(RouteStab.getRoute()));
        Mockito.when(routeRepository.existsById(RouteStab.ID)).thenReturn(true);
        try {
            ResponseTicketDTO saved = ticketV2Service.create(TicketStab.getRequestDto());

            ResponseTicketDTO result = ticketV2Service.update(TicketStab.ID, TicketStab.getRequestDto());
            Mockito.verify(ticketRepository, Mockito.atLeastOnce()).save(captor.capture());

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
        var captor = ArgumentCaptor.forClass(Ticket.class);
        RequestTicketDTO ticketDTO = TicketStab.getRequestDto();

        try {
            ResponseTicketDTO result = ticketV2Service.create(TicketStab.getRequestDto());

            Mockito.verify(ticketRepository, Mockito.atLeastOnce()).save(captor.capture());

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

    @Test
    void deleteById() {
        Mockito.when(ticketRepository.save(Mockito.any())).thenReturn(TicketStab.getTicket());
        Mockito.when(ticketRepository.existsById(TicketStab.ID)).thenReturn(true);
        Mockito.when(routeRepository.findById(Mockito.any())).thenReturn(Optional.of(RouteStab.getRoute()));
        Mockito.when(routeRepository.existsById(RouteStab.ID)).thenReturn(true);
        try {
            ticketV2Service.deleteById(TicketStab.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            ticketV2Service.create(TicketStab.getRequestDto());
            Mockito.verify(ticketRepository, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}