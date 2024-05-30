package com.practice.StayEase.dto;

import com.practice.StayEase.entity.Role;
import lombok.Data;

@Data
public class SignUpRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}
