package com.example.e_book.controller;

import com.example.e_book.dto.LoginReponse;
import com.example.e_book.dto.LoginRequest;
import com.example.e_book.entity.Account;
import com.example.e_book.repository.AccountRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountRepository accountRepository;


    @GetMapping("/encode")
    public String encodePassword(@RequestParam String raw) {
        return new BCryptPasswordEncoder().encode(raw);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Account account = accountRepository.findByUserName(request.getUsername()).orElseThrow();

        return ResponseEntity.ok().body(
                new LoginReponse("fake-jwt-token" , account.getRole().name())
        );
    }
}
