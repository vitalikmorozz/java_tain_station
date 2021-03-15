package moroz.project.train.service.V2;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import moroz.project.train.dto.Station.RequestStationDTO;
import moroz.project.train.dto.Station.ResponseStationDTO;
import moroz.project.train.entity.Station;
import moroz.project.train.interfaces.services.IStationService;
import moroz.project.train.repository.StationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StationV2Service implements IStationService {
    final StationRepository stationRepository;
    final ModelMapper modelMapper;

    @Override
    public List<ResponseStationDTO> findAll() {
        List<Station> list = stationRepository.findAll();
        return list.stream().map(e -> modelMapper.map(e, ResponseStationDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseStationDTO findById(Long id) throws NotFoundException {
        return modelMapper.map(stationRepository.findById(id).orElseThrow(() -> new NotFoundException("Station with specified id not found")), ResponseStationDTO.class);
    }

    @Override
    public ResponseStationDTO update(Long id, RequestStationDTO dto) throws NotFoundException {
        if (!stationRepository.existsById(id)) throw new NotFoundException("Station with specified id not found");

        Station entity = modelMapper.map(dto, Station.class);
        entity.setId(id);
        return modelMapper.map(stationRepository.save(entity), ResponseStationDTO.class);
    }

    @Override
    public ResponseStationDTO create(RequestStationDTO dto) {
        Station entity = modelMapper.map(dto, Station.class);
        return modelMapper.map(stationRepository.save(entity), ResponseStationDTO.class);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if (!stationRepository.existsById(id)) throw new NotFoundException("Station with specified id not found");

        stationRepository.deleteById(id);
    }
}
