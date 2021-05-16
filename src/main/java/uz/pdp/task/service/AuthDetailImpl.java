package uz.pdp.task.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthDetailImpl implements UserDetailsService {

    final PasswordEncoder passwordEncoder;

    public AuthDetailImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User("xolov_f", passwordEncoder.encode("feruz123"), new ArrayList<>()),
                new User("feruz_kh", passwordEncoder.encode("kholov"), new ArrayList<>()),
                new User("feruzXolov", passwordEncoder.encode("kholovF"), new ArrayList<>())

        ));
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        throw new UsernameNotFoundException("User not found");
    }
}
