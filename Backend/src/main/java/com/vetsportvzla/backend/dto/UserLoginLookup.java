package com.vetsportvzla.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginLookup {
    private int userId;
    private String username;
    private String fullName;
    private String email;
    private String passwordHash;
    private int roleId;
    private String roleName;
}
