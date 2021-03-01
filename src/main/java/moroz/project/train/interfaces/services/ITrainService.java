package moroz.project.train.interfaces.services;

import moroz.project.train.dto.Train.RequestTrainDTO;
import moroz.project.train.dto.Train.ResponseTrainDTO;
import moroz.project.train.interfaces.IDtoCrudService;

public interface ITrainService extends IDtoCrudService<RequestTrainDTO, ResponseTrainDTO> {
}
