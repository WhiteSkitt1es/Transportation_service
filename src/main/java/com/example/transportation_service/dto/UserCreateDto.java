package com.example.transportation_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@Schema(description = "Сущность для создания пользователя")
public class UserCreateDto {

    @Schema(description = "ФИО пользователя")
    @JsonProperty("full_name")
    String fullName;

    @Schema(description = "Логин (почта) пользователя")
    @Email
    String username;

    @Schema(description = "Пароль пользователя")
    @NotEmpty
    String password;

    @Schema(description = "Роль пользователя в системе")
    Role role;
}
