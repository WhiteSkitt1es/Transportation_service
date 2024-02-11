package com.example.transportation_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class TicketDto {

    @JsonProperty("seat_number")
    Integer seatNumber;

    LocalDateTime time;

    BigDecimal price;

    @JsonProperty("passenger_name")
    String passengerName;

    @JsonProperty("routes_id")
    Long routesId;
}
