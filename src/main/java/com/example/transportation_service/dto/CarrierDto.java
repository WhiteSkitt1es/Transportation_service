package com.example.transportation_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class CarrierDto {

    String name;

    @JsonProperty("telephone_number")
    String telephoneNumber;
}
