package com.example.transportation_service.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class PersonalTicketDto {
    Integer seatNumber;
    LocalDateTime time;
    BigDecimal price;
    String passengerName;
    String departurePoint;
    String destinationPoint;
}
