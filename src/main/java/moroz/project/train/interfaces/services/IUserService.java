package moroz.project.train.interfaces.services;

import moroz.project.train.dto.User.RequestUserDTO;
import moroz.project.train.dto.User.ResponseUserDTO;
import moroz.project.train.interfaces.IDtoCrudService;

public interface IUserService extends IDtoCrudService<RequestUserDTO, ResponseUserDTO> {
}
