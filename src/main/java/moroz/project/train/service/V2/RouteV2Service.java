package moroz.project.train.service.V2;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import moroz.project.train.dto.Route.RequestRouteDTO;
import moroz.project.train.dto.Route.ResponseRouteDTO;
import moroz.project.train.entity.Route;
import moroz.project.train.entity.Stoppage;
import moroz.project.train.exceptions.ConflictException;
import moroz.project.train.interfaces.services.IRouteService;
import moroz.project.train.repository.RouteRepository;
import moroz.project.train.repository.StoppageRepository;
import moroz.project.train.repository.TrainRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteV2Service implements IRouteService {
    final RouteRepository routeRepository;
    final TrainRepository trainRepository;
    final StoppageRepository stoppageRepository;
    final ModelMapper modelMapper;

    @Override
    public List<ResponseRouteDTO> findAll() {
        List<Route> list = routeRepository.findAll();
        return list.stream().map(e -> modelMapper.map(e, ResponseRouteDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseRouteDTO findById(Long id) throws NotFoundException {
        return modelMapper.map(routeRepository.findById(id).orElseThrow(() -> new NotFoundException("Route with specified id not found")), ResponseRouteDTO.class);
    }

    @Override
    public ResponseRouteDTO update(Long id, RequestRouteDTO dto) throws NotFoundException {
        if (!routeRepository.existsById(id)) throw new NotFoundException("Route with specified id not found");

        Route entity = modelMapper.map(dto, Route.class);
        entity.setId(id);
        entity.setTrain(trainRepository.findById(dto.getTrainId()).orElseThrow(() -> new NotFoundException("Train with specified id not found")));
        return modelMapper.map(routeRepository.save(entity), ResponseRouteDTO.class);
    }

    @Override
    public ResponseRouteDTO create(RequestRouteDTO dto) throws NotFoundException {
        Route entity = modelMapper.map(dto, Route.class);
        entity.setTrain(trainRepository.findById(dto.getTrainId()).orElseThrow(() -> new NotFoundException("Train with specified id not found")));
        return modelMapper.map(routeRepository.save(entity), ResponseRouteDTO.class);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if (!routeRepository.existsById(id)) throw new NotFoundException("Route with specified id not found");

        routeRepository.deleteById(id);
    }

    public ResponseRouteDTO addStoppageById(Long routeId, Long stoppageId) throws NotFoundException {
        if (!routeRepository.existsById(routeId)) throw new NotFoundException("Route with specified id not found");
        if (!stoppageRepository.existsById(stoppageId))
            throw new NotFoundException("Stoppage with specified id not found");

        Route route = routeRepository.getOne(routeId);
        Stoppage stoppage = stoppageRepository.getOne(stoppageId);
        if (route.getStoppages().contains(stoppage))
            throw new ConflictException("Route already have stoppage with id " + stoppageId);
        stoppage.setRoute(route);
        stoppageRepository.save(stoppage);

        return modelMapper.map(routeRepository.getOne(routeId), ResponseRouteDTO.class);
    }
}
