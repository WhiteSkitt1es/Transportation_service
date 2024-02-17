package com.example.transportation_service.http.rest;

import com.example.transportation_service.dto.CarrierDto;
import com.example.transportation_service.service.CarrierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "REST контроллер для взаимодействия с перевозчиками")
public class CarrierRestController {

    private final CarrierService carrierService;

    @PostMapping("/admin/carrier/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCarrier(@RequestBody CarrierDto carrierDto) {
        carrierService.createCarrier(carrierDto);
    }

    @Operation(
            description = "Обновление перевозчика",
            responses = {
                    @ApiResponse(
                            description = "Ok",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Not found",
                            responseCode = "404"
                    )
            }
    )
    @PutMapping("/admin/carrier/update/{id}")
    public ResponseEntity<?> updateCarrier(@PathVariable Long id, @RequestBody CarrierDto carrierDto) {
        return carrierService.updateCarrier(id, carrierDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @Operation(
            description = "Удаление перевозчика",
            responses = {
                    @ApiResponse(
                            description = "No content",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/admin/carrier/delete/{id}")
    public ResponseEntity<?> deleteCarrier(@PathVariable Long id) {
        return carrierService.deleteCarrier(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
