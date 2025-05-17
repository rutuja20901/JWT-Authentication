package com.admin.LoginRegisterData.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.admin.LoginRegisterData.dto.AuthRequest;
import com.admin.LoginRegisterData.dto.AuthResponse;
import com.admin.LoginRegisterData.dto.RegisterRequest;
import com.admin.LoginRegisterData.model.Admin;
import com.admin.LoginRegisterData.repository.AdminRepo;
import com.admin.LoginRegisterData.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public String register(RegisterRequest request) {
        Admin user = new Admin();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        adminRepo.save(user);
        return "Admin register successfully!";
    }

    public AuthResponse login(AuthRequest request) {
        try {
            System.out.println("checking.....");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()));
            UserDetails user = customUserDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtUtil.generateToken(user);
            System.out.println("AuthenticationManager class: " + authenticationManager.getClass());

            return new AuthResponse(token);
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed" + e.getMessage());
            throw new RuntimeException("Invalid username and password");
        }
    }

}
