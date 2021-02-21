package moroz.project.train.controller.api.V1;

import moroz.project.train.controller.api.BasicCrudApiController;
import moroz.project.train.entity.Ticket;
import moroz.project.train.service.BasicCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketApiController extends BasicCrudApiController<Ticket> {
    public TicketApiController(BasicCrudService<Ticket> service) {
        super(service);
    }
}
