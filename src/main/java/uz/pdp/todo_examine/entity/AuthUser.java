package uz.pdp.todo_examine.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.todo_examine.enums.Role;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "auth_user_todo")
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "full_name")
    private String fullName;
    @Column(unique = true,  nullable = false)

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
