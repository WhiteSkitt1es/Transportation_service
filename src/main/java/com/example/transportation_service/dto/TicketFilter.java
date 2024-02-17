package com.example.transportation_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Schema(description = "Сущность билета для выборки по фильтрации")
public class TicketFilter {

    @Schema(description = "Начало даты")
    @JsonProperty("from_time")
    LocalDateTime fromTime;

    @Schema(description = "Конец даты")
    @JsonProperty("to_time")
    LocalDateTime toTime;

    @Schema(description = "Пункт отправления")
    @JsonProperty("departure_point")
    String departurePoint;

    @Schema(description = "Пункт назначения")
    @JsonProperty("destination_point")
    String destinationPoint;

    @Schema(description = "Имя перевозчика")
    String name;
}
