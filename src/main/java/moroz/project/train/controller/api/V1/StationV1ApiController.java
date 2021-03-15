package moroz.project.train.controller.api.V1;

import moroz.project.train.controller.api.BasicCrudApiController;
import moroz.project.train.entity.Station;
import moroz.project.train.service.V1.StationService;

//@RestController
//@RequestMapping("/api/v1/station")
public class StationV1ApiController extends BasicCrudApiController<Station> {
    public StationV1ApiController(StationService service) {
        super(service);
    }
}
