package moroz.project.train.service;

import moroz.project.train.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService extends BasicCrudService<Ticket> {
    public TicketService(JpaRepository<Ticket, Long> repository) {
        super(repository);
    }
}
