package moroz.project.train.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moroz.project.train.interfaces.IBaseDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO implements IBaseDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
