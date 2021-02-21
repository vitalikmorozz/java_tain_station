package moroz.project.train.service;

import moroz.project.train.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BasicCrudService<User> {
    public UserService(JpaRepository<User, Long> repository) {
        super(repository);
    }
}
