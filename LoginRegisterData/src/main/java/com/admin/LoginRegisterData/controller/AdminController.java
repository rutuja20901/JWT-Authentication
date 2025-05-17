package com.admin.LoginRegisterData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.LoginRegisterData.dto.AuthRequest;
import com.admin.LoginRegisterData.dto.AuthResponse;
import com.admin.LoginRegisterData.dto.RegisterRequest;
import com.admin.LoginRegisterData.service.AdminService;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/auth/register")
    public String register(@RequestBody RegisterRequest request) {
        return adminService.register(request);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            AuthResponse response = adminService.login(request);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("hgfdsa");
            // return "throws error";
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }

    }

    @GetMapping("/admin/data")
    public String adminData() {
        return "This is Confidential admin data";
    }
}
