package com.example.transportation_service.dto;

import lombok.Value;

@Value
public class Users {
    String fullName;
    String username;
    String password;
    Role role;
}
