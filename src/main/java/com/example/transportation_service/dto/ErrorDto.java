package com.example.transportation_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
@Schema(description = "Сущность ошибки")
public class ErrorDto {
    @Schema(description = "Сообщение об ошибке")
    String message;
}
