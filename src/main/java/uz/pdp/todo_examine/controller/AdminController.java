package uz.pdp.todo_examine.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.todo_examine.entity.AuthUser;
import uz.pdp.todo_examine.repository.AuthUserRepository;

import java.util.List;

@RestController

public class AdminController {
    private final AuthUserRepository authUserRepository;

    public AdminController(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users/list")
    public ResponseEntity<List<AuthUser>> getUsers() {

        List<AuthUser> allUsers = authUserRepository.findAll();

        return ResponseEntity.ok(allUsers);
    }

}
