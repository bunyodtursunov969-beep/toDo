package uz.pdp.todo_examine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.todo_examine.entity.AuthUser;
import uz.pdp.todo_examine.entity.ToDo;
import uz.pdp.todo_examine.repository.AuthUserRepository;
import uz.pdp.todo_examine.repository.ToDoRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    private final AuthUserRepository authUserRepository;
    private final ToDoRepository toDoRepository;

    public PostController(AuthUserRepository authUserRepository, ToDoRepository toDoRepository) {
        this.authUserRepository = authUserRepository;
        this.toDoRepository = toDoRepository;
    }
   @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/create/todo")
    public ResponseEntity<Void> createTodo(@RequestParam String title, @RequestParam String content, Principal principal) {

        Optional<AuthUser> authUser = authUserRepository.findAuthUserByUsername(principal.getName());

        ToDo toDo = new ToDo();

        toDo.setTitle(title);
        toDo.setContent(content);
        toDo.setId(authUser.get().getId());

          toDoRepository.save(toDo);
          return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/my/posts")
    public ResponseEntity<List<ToDo>> getAllMyTodos(Principal principal) {

        Optional<AuthUser> authUser = authUserRepository.findAuthUserByUsername(principal.getName());

        List<ToDo> toDos = toDoRepository.findToDoByUserId(authUser.get().getId());

        return ResponseEntity.ok(toDos);
    }

}
