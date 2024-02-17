package com.example.transportation_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
@Schema(description = "Сущность перевозчик")
public class CarrierDto {

    @Schema(description = "Имя перевозчика")
    String name;

    @Size(min = 10, max = 10)
    @JsonProperty("telephone_number")
    @Schema(description = "Телефонный номер перевозчика")
    String telephoneNumber;

}
