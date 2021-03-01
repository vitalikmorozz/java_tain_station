package moroz.project.train.dto.User;

import lombok.Data;
import moroz.project.train.interfaces.IBaseDTO;

@Data
public class ResponseUserDTO implements IBaseDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
