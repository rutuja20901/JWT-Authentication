package com.admin.LoginRegisterData.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    private String username;

    private String password;
}
