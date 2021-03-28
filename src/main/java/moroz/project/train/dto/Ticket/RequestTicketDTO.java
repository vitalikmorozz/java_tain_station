package moroz.project.train.dto.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moroz.project.train.interfaces.IBaseDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestTicketDTO implements IBaseDTO {
    private String passengerFirstName;

    private String passengerLastName;

    private Long routeId;
}
