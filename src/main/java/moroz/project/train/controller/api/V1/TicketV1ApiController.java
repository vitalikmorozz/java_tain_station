package moroz.project.train.controller.api.V1;

import moroz.project.train.controller.api.BasicCrudApiController;
import moroz.project.train.entity.Ticket;
import moroz.project.train.service.V1.TicketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/api/v1/ticket")
public class TicketV1ApiController extends BasicCrudApiController<Ticket> {
    public TicketV1ApiController(TicketService service) {
        super(service);
    }
}
