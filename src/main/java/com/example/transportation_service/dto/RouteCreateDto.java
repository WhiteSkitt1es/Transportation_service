package com.example.transportation_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class RouteCreateDto {

    @JsonProperty("departure_point")
    String departurePoint;

    @JsonProperty("destination_point")
    String destinationPoint;

    @JsonProperty("id_carrier")
    Long idCarrier;
}
