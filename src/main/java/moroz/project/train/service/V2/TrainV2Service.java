package moroz.project.train.service.V2;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import moroz.project.train.dto.Train.RequestTrainDTO;
import moroz.project.train.dto.Train.ResponseTrainDTO;
import moroz.project.train.entity.Train;
import moroz.project.train.interfaces.services.ITrainService;
import moroz.project.train.repository.TrainRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainV2Service implements ITrainService {
    final TrainRepository trainRepository;
    final ModelMapper modelMapper;

    @Override
    public List<ResponseTrainDTO> findAll() {
        List<Train> list = trainRepository.findAll();
        return list.stream().map(e -> modelMapper.map(e, ResponseTrainDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseTrainDTO findById(Long id) throws NotFoundException {
        return modelMapper.map(trainRepository.findById(id).orElseThrow(() -> new NotFoundException("Train with specified id not found")), ResponseTrainDTO.class);
    }

    @Override
    public ResponseTrainDTO update(Long id, RequestTrainDTO dto) throws NotFoundException {
        if (!trainRepository.existsById(id)) throw new NotFoundException("Train with specified id not found");

        Train entity = modelMapper.map(dto, Train.class);
        entity.setId(id);
        return modelMapper.map(trainRepository.save(entity), ResponseTrainDTO.class);
    }

    @Override
    public ResponseTrainDTO create(RequestTrainDTO dto) {
        Train entity = modelMapper.map(dto, Train.class);
        return modelMapper.map(trainRepository.save(entity), ResponseTrainDTO.class);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if (!trainRepository.existsById(id)) throw new NotFoundException("Train with specified id not found");

        trainRepository.deleteById(id);
    }
}
