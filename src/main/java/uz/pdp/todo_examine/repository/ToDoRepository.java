package uz.pdp.todo_examine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.todo_examine.entity.ToDo;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    List<ToDo> findToDoByUserId(Integer userId);
}