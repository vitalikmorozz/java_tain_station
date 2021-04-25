package moroz.project.train.service.V2;

import lombok.RequiredArgsConstructor;
import moroz.project.train.dto.User.RequestUserDTO;
import moroz.project.train.entity.Role;
import moroz.project.train.entity.User;
import moroz.project.train.repository.RoleRepository;
import moroz.project.train.repository.UserRepository;
import moroz.project.train.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public User createUser(RequestUserDTO userRequest, String role) {
        var roles = new HashSet<Role>();
        var build = roleRepo.findByName(role);
        roles.add(build.orElseThrow());
        var user = User.builder()
                .email(userRequest
                        .getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(roles)
                .build();
        return userRepo.save(user);
    }

    public String createToken(RequestUserDTO request) {
        var authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return jwtUtils.generateJwtToken(authenticate);
    }
}
