package moroz.project.train.dto.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moroz.project.train.dto.Route.ResponseRouteDTO;
import moroz.project.train.dto.User.ResponseUserDTO;
import moroz.project.train.interfaces.IBaseDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTicketDTO implements IBaseDTO {
    private Long id;

    private ResponseUserDTO user;

    private String passengerFirstName;

    private String passengerLastName;

    private ResponseRouteDTO route;
}
