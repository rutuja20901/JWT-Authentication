package com.admin.LoginRegisterData.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin/api")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return adminService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return adminService.login(request);
    }

    @GetMapping("/data")
    public String adminData() {
        return "This is Confidential admin data";
    }
}
