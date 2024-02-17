package com.example.transportation_service.http.rest;

import com.example.transportation_service.dto.RouteDto;
import com.example.transportation_service.service.RouteService;
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
@Tag(name = "REST контроллер для взаимодействия с маршрутами")
public class RouteRestController {

    private final RouteService routeService;

    @PostMapping("/admin/route/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRoute(@RequestBody RouteDto routeDto) {
        routeService.createRoute(routeDto);
    }

    @Operation(
            description = "Обновление маршрута",
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
    @PutMapping("/admin/route/update/{id}")
    public ResponseEntity<?> updateRoute(@PathVariable Long id, @RequestBody RouteDto routeDto) {
        return routeService.updateRoute(id, routeDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @Operation(
            description = "Удаление маршрута",
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
    @DeleteMapping("/admin/route/delete/{id}")
    public ResponseEntity<?> deleteRoutes(@PathVariable Long id) {
        return routeService.deleteRoute(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
