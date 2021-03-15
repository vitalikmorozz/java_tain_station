package moroz.project.train.controller.api.V1;

import moroz.project.train.controller.api.BasicCrudApiController;
import moroz.project.train.entity.Route;
import moroz.project.train.service.V1.RouteService;

//@RestController
//@RequestMapping("/api/v1/route")
public class RouteV1ApiController extends BasicCrudApiController<Route> {
    public RouteV1ApiController(RouteService service) {
        super(service);
    }
}
