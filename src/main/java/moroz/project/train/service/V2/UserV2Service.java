package moroz.project.train.service.V2;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import moroz.project.train.dto.User.RequestUserDTO;
import moroz.project.train.dto.User.ResponseUserDTO;
import moroz.project.train.entity.User;
import moroz.project.train.interfaces.services.IUserService;
import moroz.project.train.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserV2Service implements IUserService {
    final UserRepository userRepository;
    final ModelMapper modelMapper;

    @Override
    public List<ResponseUserDTO> findAll() {
        List<User> list = userRepository.findAll();
        return list.stream().map(e -> modelMapper.map(e, ResponseUserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseUserDTO findById(Long id) throws NotFoundException {
        return modelMapper.map(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with specified id not found")), ResponseUserDTO.class);
    }

    @Override
    public ResponseUserDTO update(Long id, RequestUserDTO dto) throws NotFoundException {
        if (!userRepository.existsById(id)) throw new NotFoundException("User with specified id not found");

        User entity = modelMapper.map(dto, User.class);
        entity.setId(id);
        return modelMapper.map(userRepository.save(entity), ResponseUserDTO.class);
    }

    @Override
    public ResponseUserDTO create(RequestUserDTO dto) {
        User entity = modelMapper.map(dto, User.class);
        return modelMapper.map(userRepository.save(entity), ResponseUserDTO.class);
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        if (!userRepository.existsById(id)) throw new NotFoundException("User with specified id not found");

        userRepository.deleteById(id);
    }
}
