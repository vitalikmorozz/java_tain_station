package moroz.project.train.controller.api.V2;

import moroz.project.train.controller.api.DtoCrudApiController;
import moroz.project.train.dto.Ticket.RequestTicketDTO;
import moroz.project.train.dto.Ticket.ResponseTicketDTO;
import moroz.project.train.entity.Ticket;
import moroz.project.train.service.V2.TicketV2Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/ticket")
public class TicketV2Controller extends DtoCrudApiController<Ticket, RequestTicketDTO, ResponseTicketDTO> {
    public TicketV2Controller(TicketV2Service service) {
        super(service);
    }
}
