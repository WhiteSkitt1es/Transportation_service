package com.example.transportation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class ErrorDto {
    String message;
}
