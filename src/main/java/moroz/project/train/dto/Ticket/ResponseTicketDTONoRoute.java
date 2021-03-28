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
public class ResponseTicketDTONoRoute implements IBaseDTO {
    private Long id;

    private String passengerFirstName;

    private String passengerLastName;
}
