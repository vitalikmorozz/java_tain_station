package moroz.project.train.controller.api.V1;

import moroz.project.train.controller.api.BasicCrudApiController;
import moroz.project.train.entity.Train;
import moroz.project.train.service.V1.TrainService;

//@RestController
//@RequestMapping("/api/v1/train")
public class TrainV1ApiController extends BasicCrudApiController<Train> {
    public TrainV1ApiController(TrainService service) {
        super(service);
    }
}
