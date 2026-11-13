package uz.pdp.todo_examine.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.todo_examine.dtos.RegisterDto;
import uz.pdp.todo_examine.entity.AuthUser;
import uz.pdp.todo_examine.enums.Role;
import uz.pdp.todo_examine.exceptions.UserAlreadyExistsException;
import uz.pdp.todo_examine.repository.AuthUserRepository;

import java.util.Optional;

@Component
  public class AuthService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(@RequestBody RegisterDto dto){

        Optional<AuthUser> authUser = authUserRepository.findAuthUserByUsername(dto.username());

        if(authUser.isPresent()) throw new UserAlreadyExistsException(dto.username() +" already exists");

         AuthUser authUser1 = new AuthUser();

         authUser1.setFullName(dto.fullName());
         authUser1.setUsername(dto.username());
         authUser1.setPassword( passwordEncoder.encode(dto.password()) );
         authUser1.setRole(Role.ROLE_ADMIN);

         authUserRepository.save(authUser1);


    }

}
