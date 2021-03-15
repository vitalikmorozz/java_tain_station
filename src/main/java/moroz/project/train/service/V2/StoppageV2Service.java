package moroz.project.train.service.V2;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import moroz.project.train.dto.Stoppage.RequestStoppageDTO;
import moroz.project.train.dto.Stoppage.ResponseStoppageDTO;
import moroz.project.train.entity.Stoppage;
import moroz.project.train.interfaces.services.IStoppageService;
import moroz.project.train.repository.StationRepository;
import moroz.project.train.repository.StoppageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoppageV2Service implements IStoppageService {
    final StoppageRepository stoppageRepository;
    final StationRepository stationRepository;
    final ModelMapper modelMapper;

    @Override
    public List<ResponseStoppageDTO> findAll() {
        List<Stoppage> list = stoppageRepository.findAll();
        return list.stream().map(e -> modelMapper.map(e, ResponseStoppageDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseStoppageDTO findById(Long id) throws NotFoundException {
        return modelMapper.map(stoppageRepository.findById(id).orElseThrow(() -> new NotFoundException("Stoppage with specified id not found")), ResponseStoppageDTO.class);
    }

    @Override
    public ResponseStoppageDTO update(Long id, RequestStoppageDTO dto) throws NotFoundException {
        if (!stoppageRepository.existsById(id)) throw new NotFoundException("Stoppage with specified id not found");

        Stoppage entity = modelMapper.map(dto, Stoppage.class);
        entity.setId(id);
        entity.setStation(stationRepository.findById(dto.getStationId()).orElseThrow(() -> new NotFoundException("Station with specified id not found")));
        return modelMapper.map(stoppageRepository.save(entity), ResponseStoppageDTO.class);
    }

    @Override
    public ResponseStoppageDTO create(RequestStoppageDTO dto) throws NotFoundException {
        Stoppage entity = modelMapper.map(dto, Stoppage.class);
        entity.setStation(stationRepository.findById(dto.getStationId()).orElseThrow(() -> new NotFoundException("Station with specified id not found")));
        return modelMapper.map(stoppageRepository.save(entity), ResponseStoppageDTO.class);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if (!stoppageRepository.existsById(id)) throw new NotFoundException("Stoppage with specified id not found");

        stoppageRepository.deleteById(id);
    }
}
