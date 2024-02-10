package com.example.transportation_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
public class UserCreateDto {

    @JsonProperty("full_name")
    String fullName;

    @Email
    String username;

    @NotEmpty
    String password;

    Role role;
}
