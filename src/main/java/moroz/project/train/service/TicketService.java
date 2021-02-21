package moroz.project.train.service;

import moroz.project.train.entity.Ticket;
import moroz.project.train.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService extends BasicCrudService<Ticket> {
    public TicketService(TicketRepository repository) {
        super(repository);
    }
}
