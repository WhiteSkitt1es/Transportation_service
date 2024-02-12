package com.example.transportation_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class CarrierDto {

    String name;

    @Size(min = 10, max = 10)
    @JsonProperty("telephone_number")
    String telephoneNumber;

}
