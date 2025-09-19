package com.tharun.employee.crud.dto;


import com.tharun.employee.crud.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;

}
