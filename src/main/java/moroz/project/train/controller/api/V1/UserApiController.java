package moroz.project.train.controller.api.V1;

import moroz.project.train.controller.api.BasicCrudApiController;
import moroz.project.train.entity.User;
import moroz.project.train.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserApiController extends BasicCrudApiController<User> {
    public UserApiController(UserService service) {
        super(service);
    }
}
