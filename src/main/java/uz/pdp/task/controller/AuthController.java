package uz.pdp.task.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.task.dto.LoginDTO;
import uz.pdp.task.jwt.JWTProvider;
import uz.pdp.task.service.AuthDetail;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    final AuthDetail authService;
    final JWTProvider jwtProvider;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;

    public AuthController(AuthDetail authService,
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
}
