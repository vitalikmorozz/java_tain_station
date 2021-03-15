package moroz.project.train.controller.api.V1;

import moroz.project.train.controller.api.BasicCrudApiController;
import moroz.project.train.entity.User;
import moroz.project.train.service.V1.UserService;

//@RestController
//@RequestMapping("/api/v1/users")
public class UserV1ApiController extends BasicCrudApiController<User> {
    public UserV1ApiController(UserService service) {
        super(service);
    }
}
