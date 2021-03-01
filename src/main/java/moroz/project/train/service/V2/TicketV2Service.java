package moroz.project.train.service.V2;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import moroz.project.train.dto.Ticket.RequestTicketDTO;
import moroz.project.train.dto.Ticket.ResponseTicketDTO;
import moroz.project.train.entity.Ticket;
import moroz.project.train.interfaces.services.ITicketService;
import moroz.project.train.repository.RouteRepository;
import moroz.project.train.repository.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketV2Service implements ITicketService {
    final TicketRepository ticketRepository;
    final RouteRepository routeRepository;
    final ModelMapper modelMapper;

    @Override
    public List<ResponseTicketDTO> findAll() {
        List<Ticket> list = ticketRepository.findAll();
        return list.stream().map(e -> modelMapper.map(e, ResponseTicketDTO.class)).collect(Collectors.toList());

    }

    @Override
    public ResponseTicketDTO findById(Long id) throws NotFoundException {
        if(!ticketRepository.existsById(id)) throw new NotFoundException("Ticket with specified id not found");

        return modelMapper.map(ticketRepository.findById(id).orElse(null), ResponseTicketDTO.class);
    }

    @Override
    public ResponseTicketDTO update(Long id, RequestTicketDTO dto) throws NotFoundException {
        if(!ticketRepository.existsById(id)) throw new NotFoundException("Ticket with specified id not found");
        if(!routeRepository.existsById(dto.getRouteId())) throw new NotFoundException("Route with specified id not found");

        Ticket entity = modelMapper.map(dto, Ticket.class);
        entity.setId(id);
        entity.setRoute(routeRepository.getOne(dto.getRouteId()));
        return modelMapper.map(ticketRepository.save(entity), ResponseTicketDTO.class);
    }

    @Override
    public ResponseTicketDTO create(RequestTicketDTO dto) throws NotFoundException {
        if(!routeRepository.existsById(dto.getRouteId())) throw new NotFoundException("Route with specified id not found");

        Ticket entity = modelMapper.map(dto, Ticket.class);
        entity.setRoute(routeRepository.getOne(dto.getRouteId()));
        return modelMapper.map(ticketRepository.save(entity), ResponseTicketDTO.class);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if(!ticketRepository.existsById(id)) throw new NotFoundException("Ticket with specified id not found");

        ticketRepository.deleteById(id);
    }
}
