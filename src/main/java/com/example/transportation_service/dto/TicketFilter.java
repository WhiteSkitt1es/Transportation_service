package com.example.transportation_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class TicketFilter {

    @JsonProperty("from_time")
    LocalDateTime fromTime;

    @JsonProperty("to_time")
    LocalDateTime toTime;

    @JsonProperty("departure_point")
    String departurePoint;

    @JsonProperty("destination_point")
    String destinationPoint;

    String name;
}
