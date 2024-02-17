package com.example.transportation_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Value;

@Value
@Schema(description = "Сущность маршрут")
public class RouteDto {

    @Schema(description = "Пункт отправления")
    @JsonProperty("departure_point")
    String departurePoint;

    @Schema(description = "Пункт назначения")
    @JsonProperty("destination_point")
    String destinationPoint;

    @Schema(description = "Идентификатор перевозчика")
    @JsonProperty("id_carrier")
    Long idCarrier;
}
