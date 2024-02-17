package com.example.transportation_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Schema(description = "Сущность созданного билета")
public class TicketDto {

    @Schema(description = "Номер места пассажира")
    @JsonProperty("seat_number")
    Integer seatNumber;

    @Schema(description = "Время рейса")
    LocalDateTime time;

    @Schema(description = "Стоимость биллета")
    BigDecimal price;

    @Schema(description = "Идентификатор маршрута")
    @JsonProperty("routes_id")
    Long routesId;
}
