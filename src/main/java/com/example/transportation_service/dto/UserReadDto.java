package com.example.transportation_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(description = "Сущность для получения информации")
public class UserReadDto {

    @Schema(description = "Идентификатор пользователя")
    Long id;

    @Schema(description = "ФИО пользователя")
    String fullName;

    @Schema(description = "Логин (почта) пользователя")
    String username;

    @Schema(description = "Роль пользователя в системе")
    Role role;
}
