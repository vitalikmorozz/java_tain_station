package moroz.project.train.stabs;


import moroz.project.train.dto.User.RequestUserDTO;
import moroz.project.train.dto.User.ResponseUserDTO;
import moroz.project.train.entity.User;

import java.util.ArrayList;

public final class UserStab {
    public static final Long ID = 1L;
    public static User getUser() {
        return User.builder().id(ID).email("email@text.com").firstName("first").lastName("last").password("password").tickets(new ArrayList<>()).build();
    }

    public static RequestUserDTO getRequestDto() {
        return RequestUserDTO.builder().email("email@text.com").firstName("first").lastName("last").password("password").build();
    }

    public static ResponseUserDTO getResponseDto() {
        return ResponseUserDTO.builder().id(ID).email("email@text.com").firstName("first").lastName("last").password("password").build();
    }
}
