package moroz.project.train.controller.api.V2;

import moroz.project.train.controller.api.DtoCrudApiController;
import moroz.project.train.dto.Station.RequestStationDTO;
import moroz.project.train.dto.Station.ResponseStationDTO;
import moroz.project.train.entity.Station;
import moroz.project.train.service.V2.StationV2Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/station")
public class StationV2ApiController extends DtoCrudApiController<Station, RequestStationDTO, ResponseStationDTO> {
    public StationV2ApiController(StationV2Service service) {
        super(service);
    }
}
