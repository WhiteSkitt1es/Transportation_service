package com.example.transportation_service.http.rest;

import com.example.transportation_service.dto.*;
import com.example.transportation_service.service.CarrierService;
import com.example.transportation_service.service.RouteService;
import com.example.transportation_service.service.TicketService;
import com.example.transportation_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "REST контроллер, для взаимодействия с микросервисом - Транспортные биллеты")
public class TransportationRestController {

    private final UserService userService;
    private final TicketService ticketService;
    private final RouteService routeService;
    private final CarrierService carrierService;

    @Operation(
            description = "Создание нового пользователя",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201"
                    )
            }
    )
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Validated UserCreateDto user) {
        userService.createUser(user);
    }

    @Operation(
            description = "Получение списка всех доступных для покупки билетов, с возможностью пагинации (страница/размер) и фильтрации"
    )
    @GetMapping("/tickets")
    public List<PersonalTicketDto> findAll(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit,
                                           @RequestBody TicketFilter filter) {

        return ticketService.findAll(offset, limit, filter);
    }

    @Operation(
            description = "Покупка определенного билета",
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
    @PutMapping("/{id}/buy")
    public ResponseEntity<?> byuTicket(@PathVariable("id") Long id, @RequestParam("full_name") String fullName) {
        return ticketService.buyTicket(id, fullName)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @Operation(
            description = "Получение списка всех купленных билетов для текущего пользователя"
    )
    @GetMapping("/buyTickets")
    public List<PersonalTicketDto> findAllByTicketsByFullName(@RequestParam("full_name") String fullName) {
        return ticketService.findAllByTicketsByFullName(fullName);
    }

    @Operation(
            description = "Создание перевозчика",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201"
                    )
            }
    )
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

    @Operation(
            description = "Создание маршрута",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201"
                    )
            }
    )
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

    @Operation(
            description = "Создание билета",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201"
                    )
            }
    )
    @PostMapping("/admin/ticket/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTicket(@RequestBody TicketDto ticketDto) {
        ticketService.createTicket(ticketDto);
    }

    @Operation(
            description = "Обновление билета",
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
    @PutMapping("/admin/ticket/update/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable Long id, @RequestBody @Validated TicketDto ticketDto) {
        return ticketService.updateTicket(id, ticketDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @Operation(
            description = "Удаление билета",
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
    @DeleteMapping("/admin/ticket/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Long id) {
        return ticketService.deleteTicket(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
