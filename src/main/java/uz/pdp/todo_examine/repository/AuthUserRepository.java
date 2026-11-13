package uz.pdp.todo_examine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.todo_examine.entity.AuthUser;

import java.util.List;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {


    Optional<AuthUser> findAuthUserByUsername(String username);


}