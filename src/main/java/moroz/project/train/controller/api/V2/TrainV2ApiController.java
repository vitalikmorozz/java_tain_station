package moroz.project.train.controller.api.V2;

import moroz.project.train.controller.api.DtoCrudApiController;
import moroz.project.train.dto.Train.RequestTrainDTO;
import moroz.project.train.dto.Train.ResponseTrainDTO;
import moroz.project.train.entity.Train;
import moroz.project.train.service.V2.TrainV2Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/train")
public class TrainV2ApiController extends DtoCrudApiController<Train, RequestTrainDTO, ResponseTrainDTO> {
    public TrainV2ApiController(TrainV2Service service) {
        super(service);
    }
}
