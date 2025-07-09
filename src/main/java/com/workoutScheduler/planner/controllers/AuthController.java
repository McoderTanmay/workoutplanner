package com.workoutScheduler.planner.controllers;

import com.workoutScheduler.planner.dto.LoginRequest;
import com.workoutScheduler.planner.dto.SignupRequest;
import com.workoutScheduler.planner.models.Users;
import com.workoutScheduler.planner.repo.UserRepository;
import com.workoutScheduler.planner.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request){
        if(userRepo.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("Email already exist");
        }
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setFullname(request.getFullname());
        user.setPassword(encoder.encode(request.getPassword()));

        userRepo.save(user);
        return ResponseEntity.ok("User successfully registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        Optional<Users> userOpt = userRepo.findByEmail(request.getEmail());

        if(userOpt.isEmpty() || !encoder.matches(request.getPassword(), userOpt.get().getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        String token = jwtUtil.generateToken(userOpt.get().getEmail());
        return ResponseEntity.ok("token: "+token);
    }
}
