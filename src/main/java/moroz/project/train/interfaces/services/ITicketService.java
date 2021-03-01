package moroz.project.train.interfaces.services;

import moroz.project.train.dto.Ticket.RequestTicketDTO;
import moroz.project.train.dto.Ticket.ResponseTicketDTO;
import moroz.project.train.interfaces.IDtoCrudService;

public interface ITicketService extends IDtoCrudService<RequestTicketDTO, ResponseTicketDTO> {
}
