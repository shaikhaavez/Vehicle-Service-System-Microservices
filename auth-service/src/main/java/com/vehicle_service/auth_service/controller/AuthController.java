package com.vehicle_service.auth_service.controller;

import com.vehicle_service.auth_service.entity.Role;
import com.vehicle_service.auth_service.entity.User;
import com.vehicle_service.auth_service.repository.UserRepository;
import com.vehicle_service.auth_service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/test-secure")
    public ResponseEntity<String> testSecure() {
        return ResponseEntity.ok("Access granted to secure endpoint!");
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);

        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.get("email"), credentials.get("password"))
            );

            UserDetails userDetails = userRepository.findByEmail(credentials.get("email"))
                    .map(user -> new org.springframework.security.core.userdetails.User(
                            user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().name())))
                    ).orElseThrow(() ->
                            new UsernameNotFoundException("User not found")
                    );

            return ResponseEntity.ok(jwtService.generateToken(userDetails));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }

    }

}
