package moroz.project.train.controller.api.V1;

import moroz.project.train.controller.api.BasicCrudApiController;
import moroz.project.train.entity.Stoppage;
import moroz.project.train.service.BasicCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stoppage")
public class StoppageApiController extends BasicCrudApiController<Stoppage> {
    public StoppageApiController(BasicCrudService<Stoppage> service) {
        super(service);
    }
}
