package com.example.transportation_service.dto;

import lombok.Value;

@Value
public class UserReadDto {
    Long id;
    String fullName;
    String username;
    Role role;
}
