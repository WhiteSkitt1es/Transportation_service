package com.example.transportation_service.http.rest;

import com.example.transportation_service.dto.*;
import com.example.transportation_service.service.TransportationService;
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

    private final TransportationService transportationService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Validated UserCreateDto user) {
         transportationService.createUser(user);
    }

    @GetMapping("/tickets")
    public List<PersonalTicketDto> findAll(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit,
                                           @RequestBody TicketFilter filter) {

        return transportationService.findAll(offset, limit, filter);
    }

    @PutMapping("/{id}/buy")
    @ResponseStatus(HttpStatus.OK)
    public void byuTicket(@PathVariable("id") Long id, @RequestParam("full_name") String fullName) {
        transportationService.buyTicket(id, fullName);
    }

    @GetMapping("/buyTickets")
    public List<PersonalTicketDto> findAllByTicketsByFullName(@RequestParam("full_name") String fullName) {
        return transportationService.findAllByTicketsByFullName(fullName);
    }

    @PostMapping("/admin/carriers/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCarrier(@RequestBody CarrierCreateDto carrierCreateDto) {

    }

    @PutMapping("/admin/carriers/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCarrier(@PathVariable Long id, @RequestBody CarrierCreateDto carrierCreateDto) {

    }

    @DeleteMapping("/admin/carriers/delete/{id}")
    public ResponseEntity<?> deleteCarrier(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/admin/routes/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRoute(@RequestBody RouteCreateDto routeCreateDto) {

    }

    @PutMapping("/admin/routes/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRoute(@PathVariable Long id, @RequestBody RouteCreateDto routeCreateDto) {

    }

    @DeleteMapping("/admin/routes/delete/{id}")
    public ResponseEntity<?> deleteRoutes(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/admin/ticket/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTicket(@RequestBody TicketCreateDto ticketCreateDto) {

    }

    @PutMapping("/admin/ticket/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTicket(@PathVariable Long id, @RequestBody TicketCreateDto ticketCreateDto) {

    }

    @DeleteMapping("/admin/ticket/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

}
