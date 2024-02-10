package com.example.transportation_service.http.rest;

import com.example.transportation_service.dto.PersonalTicketDto;
import com.example.transportation_service.dto.TicketFilter;
import com.example.transportation_service.dto.UserCreateDto;
import com.example.transportation_service.service.TransportationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public int createUser(@RequestBody @Validated UserCreateDto user) {
         return transportationService.createUser(user);
    }

    @GetMapping("/tickets")
    public List<PersonalTicketDto> findAll(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit,
                                           @RequestBody TicketFilter filter) {

        return transportationService.findAll(offset, limit, filter);
    }

    @PutMapping("/{id}/buy")
    @ResponseStatus(HttpStatus.OK)
    public int byuTicket(@PathVariable("id") Long id, @RequestParam("full_name") String fullName) {
        return transportationService.buyTicket(id, fullName);
    }

    @GetMapping("/buyTickets")
    public List<PersonalTicketDto> findAllByTicketsByFullName(@RequestParam("full_name") String fullName) {
        return transportationService.findAllByTicketsByFullName(fullName);
    }
}
