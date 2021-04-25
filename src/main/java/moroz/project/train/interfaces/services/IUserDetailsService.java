package moroz.project.train.interfaces.services;

import javassist.NotFoundException;
import moroz.project.train.entity.User;

public interface IUserDetailsService {
    User loadUserByEmail(String email) throws NotFoundException;
}
