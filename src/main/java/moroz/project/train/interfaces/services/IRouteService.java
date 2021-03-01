package moroz.project.train.interfaces.services;

import javassist.NotFoundException;
import moroz.project.train.dto.Route.RequestRouteDTO;
import moroz.project.train.dto.Route.ResponseRouteDTO;
import moroz.project.train.interfaces.IDtoCrudService;

public interface IRouteService extends IDtoCrudService<RequestRouteDTO, ResponseRouteDTO> {
    ResponseRouteDTO addStoppageById(Long routeId, Long stoppageId) throws NotFoundException;
}
