package com.example.transportation_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Schema(description = "Сущность приобретенного билета")
public class PersonalTicketDto {

    @Schema(description = "Номер места пассажира")
    Integer seatNumber;

    @Schema(description = "Время рейса")
    LocalDateTime time;

    @Schema(description = "Стоимость биллета")
    BigDecimal price;

    @Schema(description = "Имя пассажира которому принадлежит билет")
    String passengerName;

    @Schema(description = "Пункт отправления")
    String departurePoint;

    @Schema(description = "Пункт назначения")
    String destinationPoint;
}
