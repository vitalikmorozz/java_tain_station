package moroz.project.train.service;

import moroz.project.train.entity.User;
import moroz.project.train.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BasicCrudService<User> {
    public UserService(UserRepository repository) {
        super(repository);
    }
}
