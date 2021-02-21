package moroz.project.train.controller.api.V1;

import moroz.project.train.controller.api.BasicCrudApiController;
import moroz.project.train.entity.Route;
import moroz.project.train.service.BasicCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/route")
public class RouteApiController extends BasicCrudApiController<Route> {
    public RouteApiController(BasicCrudService<Route> service) {
        super(service);
    }
}
