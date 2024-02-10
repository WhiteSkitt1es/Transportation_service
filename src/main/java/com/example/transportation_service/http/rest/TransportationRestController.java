package com.example.transportation_service.http.rest;

import com.example.transportation_service.dto.PersonalTicketDto;
import com.example.transportation_service.dto.TicketFilter;
import com.example.transportation_service.dto.UserCreateDto;
import com.example.transportation_service.dto.UserReadDto;
import com.example.transportation_service.service.TransportationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class TransportationRestController {

    private final TransportationService transportationService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public int createUser(@RequestBody UserCreateDto user) {
         return transportationService.createUser(user);
    }

    @GetMapping("/tickets")
    public Page<PersonalTicketDto> findAll(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit,
                                           @RequestBody TicketFilter filter) {
        return null;
    }

    @GetMapping("/{id}/tickets")
    public List<PersonalTicketDto> findAllByTicketsById(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/buy/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonalTicketDto byuTicket(@PathVariable Long id) {
        return null;
    }
}
