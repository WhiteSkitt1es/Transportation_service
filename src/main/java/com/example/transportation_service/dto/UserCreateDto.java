package com.example.transportation_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
public class UserCreateDto {

    @JsonProperty("full_name")
    String fullName;

    String username;

    String password;

    Role role;
}
