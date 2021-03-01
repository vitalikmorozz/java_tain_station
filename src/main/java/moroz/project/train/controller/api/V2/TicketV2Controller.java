package moroz.project.train.controller.api.V2;

import moroz.project.train.controller.api.DtoCrudApiController;
import moroz.project.train.dto.Train.RequestTrainDTO;
import moroz.project.train.dto.Train.ResponseTrainDTO;
import moroz.project.train.entity.Train;
import moroz.project.train.service.V2.TrainV2Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/ticket")
public class TicketV2Controller extends DtoCrudApiController<Train, RequestTrainDTO, ResponseTrainDTO> {
    public TicketV2Controller(TrainV2Service service) {
        super(service);
    }
}
