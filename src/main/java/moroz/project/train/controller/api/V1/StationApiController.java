package moroz.project.train.controller.api.V1;

import moroz.project.train.controller.api.BasicCrudApiController;
import moroz.project.train.entity.Station;
import moroz.project.train.service.StationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/station")
public class StationApiController extends BasicCrudApiController<Station> {
    public StationApiController(StationService service) {
        super(service);
    }
}
