package moroz.project.train.controller.api.V1;

import moroz.project.train.controller.api.BasicCrudApiController;
import moroz.project.train.entity.Train;
import moroz.project.train.service.TrainService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/train")
public class TrainApiController extends BasicCrudApiController<Train> {
    public TrainApiController(TrainService service) {
        super(service);
    }
}
