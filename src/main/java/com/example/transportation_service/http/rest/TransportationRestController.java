package com.example.transportation_service.http.rest;

import com.example.transportation_service.dto.*;
import com.example.transportation_service.service.CarrierService;
import com.example.transportation_service.service.RouteService;
import com.example.transportation_service.service.TicketService;
import com.example.transportation_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class TransportationRestController {

    private final UserService userService;
    private final TicketService ticketService;
    private final RouteService routeService;
    private final CarrierService carrierService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Validated UserCreateDto user) {
        userService.createUser(user);
    }

    @GetMapping("/tickets")
    public List<PersonalTicketDto> findAll(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit,
                                           @RequestBody TicketFilter filter) {

        return ticketService.findAll(offset, limit, filter);
    }

    @PutMapping("/{id}/buy")
    public ResponseEntity<?> byuTicket(@PathVariable("id") Long id, @RequestParam("full_name") String fullName) {
        return ticketService.buyTicket(id, fullName)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/buyTickets")
    public List<PersonalTicketDto> findAllByTicketsByFullName(@RequestParam("full_name") String fullName) {
        return ticketService.findAllByTicketsByFullName(fullName);
    }

    @PostMapping("/admin/carrier/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCarrier(@RequestBody CarrierDto carrierDto) {
        carrierService.createCarrier(carrierDto);
    }

    @PutMapping("/admin/carrier/update/{id}")
    public ResponseEntity<?> updateCarrier(@PathVariable Long id, @RequestBody CarrierDto carrierDto) {
        return carrierService.updateCarrier(id,carrierDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/admin/carrier/delete/{id}")
    public ResponseEntity<?> deleteCarrier(@PathVariable Long id) {
        return carrierService.deleteCarrier(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/admin/route/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRoute(@RequestBody RouteDto routeDto) {
        routeService.createRoute(routeDto);
    }

    @PutMapping("/admin/route/update/{id}")
    public ResponseEntity<?> updateRoute(@PathVariable Long id, @RequestBody RouteDto routeDto) {
        return routeService.updateRoute(id, routeDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/admin/route/delete/{id}")
    public ResponseEntity<?> deleteRoutes(@PathVariable Long id) {
        return routeService.deleteRoute(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/admin/ticket/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTicket(@RequestBody TicketDto ticketDto) {
        ticketService.createTicket(ticketDto);
    }

    @PutMapping("/admin/ticket/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateTicket(@PathVariable Long id, @RequestBody @Validated TicketDto ticketDto) {
        return ticketService.updateTicket(id, ticketDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/admin/ticket/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Long id) {
        return ticketService.deleteTicket(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
