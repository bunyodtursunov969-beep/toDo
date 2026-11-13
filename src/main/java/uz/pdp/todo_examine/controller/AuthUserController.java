package uz.pdp.todo_examine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.todo_examine.config.JwtTokenUtil;
import uz.pdp.todo_examine.dtos.LoginDto;
import uz.pdp.todo_examine.dtos.RegisterDto;
import uz.pdp.todo_examine.service.AuthService;

@RestController
@RequestMapping("/api/auth/**")
public class AuthUserController {


    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthUserController(AuthService authService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody RegisterDto dto) {
           authService.createUser(dto);

           return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping("/login")
 public String login(@RequestBody LoginDto dto){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());

       authenticationManager.authenticate(authenticationToken);

        return jwtTokenUtil.generateToken(dto.username());
    }

}
