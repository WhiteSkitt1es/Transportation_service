package com.example.transportation_service.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Value;

@Value
@Hidden
public class Users {
    String fullName;
    String username;
    String password;
    Role role;
}
