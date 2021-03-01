package moroz.project.train.dto.Route;

import lombok.Data;
import moroz.project.train.dto.Stoppage.ResponseStoppageDTO;
import moroz.project.train.dto.Ticket.ResponseTicketDTONoRoute;
import moroz.project.train.dto.Train.ResponseTrainDTO;
import moroz.project.train.interfaces.IBaseDTO;

import java.util.List;

@Data
public class ResponseRouteDTO implements IBaseDTO {
    private Long id;

    private ResponseTrainDTO train;

    private List<ResponseStoppageDTO> stoppages;

    private List<ResponseTicketDTONoRoute> tickets;
}
