package moroz.project.train.controller.api.V1;

import moroz.project.train.controller.api.BasicCrudApiController;
import moroz.project.train.entity.Stoppage;
import moroz.project.train.service.V1.StoppageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/api/v1/stoppage")
public class StoppageV1ApiController extends BasicCrudApiController<Stoppage> {
    public StoppageV1ApiController(StoppageService service) {
        super(service);
    }
}
