package uz.pdp.todo_examine.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.pdp.todo_examine.entity.AuthUser;
import uz.pdp.todo_examine.repository.AuthUserRepository;

import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {


    private final AuthUserRepository authUserRepository;

    public CustomUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AuthUser> optional = authUserRepository.findAuthUserByUsername(username);

        AuthUser authUser = optional.orElseThrow(() -> new UsernameNotFoundException("Username not found with username: " + username));

        return new CustomUserDetails(authUser);
    }
}
