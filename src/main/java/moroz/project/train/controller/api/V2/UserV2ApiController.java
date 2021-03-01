package moroz.project.train.controller.api.V2;

import moroz.project.train.controller.api.DtoCrudApiController;
import moroz.project.train.dto.User.RequestUserDTO;
import moroz.project.train.dto.User.ResponseUserDTO;
import moroz.project.train.entity.User;
import moroz.project.train.service.V2.UserV2Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/users")
public class UserV2ApiController extends DtoCrudApiController<User, RequestUserDTO, ResponseUserDTO> {
    public UserV2ApiController(UserV2Service service) {
        super(service);
    }
}
