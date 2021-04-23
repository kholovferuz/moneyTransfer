package uz.pdp.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthDetail authService;
    @Autowired
    JWTProvider jwtProvider;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            String token = jwtProvider.generateToken(loginDTO.getUsername());
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException exception) {
            return ResponseEntity.status(401).body("Username or password is not correct");
        }
    }
}
