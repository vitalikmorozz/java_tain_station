package moroz.project.train.stabs;

import moroz.project.train.dto.Ticket.RequestTicketDTO;
import moroz.project.train.dto.Ticket.ResponseTicketDTO;
import moroz.project.train.entity.Ticket;

import java.time.LocalDateTime;

public final class TicketStab {
    public static final Long ID = 1L;
    public static final LocalDateTime arrivalTime = LocalDateTime.now();
    public static final LocalDateTime departureTime = LocalDateTime.now();
    public static Ticket getTicket() {
        return Ticket.builder().id(ID)
                .passengerFirstName("first")
                .passengerLastName("second")
                .user(UserStab.getUser())
                .route(RouteStab.getRoute())
                .build();
    }

    public static RequestTicketDTO getRequestDto() {
        return RequestTicketDTO.builder()
                .passengerFirstName("first")
                .passengerLastName("second")
                .routeId(RouteStab.ID)
                .build();
    }

    public static ResponseTicketDTO getResponseDto() {
        return ResponseTicketDTO.builder().id(ID)
                .passengerFirstName("first")
                .passengerLastName("second")
                .user(UserStab.getResponseDto())
                .route(RouteStab.getResponseDto())
                .build();
    }
}
