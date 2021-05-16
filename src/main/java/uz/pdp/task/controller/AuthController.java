package uz.pdp.task.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task.dto.LoginDTO;
import uz.pdp.task.jwt.JWTProvider;
import uz.pdp.task.service.AuthDetailImpl;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    final AuthDetailImpl authService;
    final JWTProvider jwtProvider;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;

    public AuthController(AuthDetailImpl authService,
                          JWTProvider jwtProvider,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            String token = jwtProvider.generateToken(loginDTO.getUsername());
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException exception) {
            return ResponseEntity.status(401).body("Username or password is not correct");
        }
    }

    // EXEPTION HANDLER
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidException(MethodArgumentNotValidException ex) {
        Map<String, String> mistakes = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            mistakes.put(fieldName, errorMessage);
        });
        return mistakes;
    }
}
