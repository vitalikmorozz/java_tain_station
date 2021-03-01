package moroz.project.train.controller.api.V2;

import moroz.project.train.controller.api.DtoCrudApiController;
import moroz.project.train.dto.Stoppage.RequestStoppageDTO;
import moroz.project.train.dto.Stoppage.ResponseStoppageDTO;
import moroz.project.train.entity.Stoppage;
import moroz.project.train.service.V2.StoppageV2Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/stoppage")
public class StoppageV2Controller extends DtoCrudApiController<Stoppage, RequestStoppageDTO, ResponseStoppageDTO> {
    public StoppageV2Controller(StoppageV2Service service) {
        super(service);
    }
}
