package moroz.project.train.dto.Ticket;

import lombok.Data;
import moroz.project.train.interfaces.IBaseDTO;

@Data
public class RequestTicketDTO implements IBaseDTO {
    private String passengerFirstName;

    private String passengerLastName;

    private Long routeId;
}
