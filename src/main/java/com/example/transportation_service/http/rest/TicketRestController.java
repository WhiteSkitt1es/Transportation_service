package com.example.transportation_service.http.rest;

import com.example.transportation_service.dto.PersonalTicketDto;
import com.example.transportation_service.dto.TicketDto;
import com.example.transportation_service.dto.TicketFilter;
import com.example.transportation_service.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@Tag(name = "REST контроллер для взаимодействия с биллетами")
public class TicketRestController {

    private final TicketService ticketService;

    @Operation(
            description = "Получение списка всех доступных для покупки билетов, с возможностью пагинации (страница/размер) и фильтрации"
    )
    @GetMapping("/allTickets")
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
    @PutMapping("/buy/{id}")
    public ResponseEntity<?> byuTicket(@PathVariable("id") Long id, @RequestParam("full_name") String fullName) {
        return ticketService.buyTicket(id, fullName)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @Operation(
            description = "Получение списка всех купленных билетов для текущего пользователя"
    )
    @GetMapping("/tickets")
    public List<PersonalTicketDto> findAllByTicketsByFullName(@RequestParam("full_name") String fullName) {
        return ticketService.findAllByTicketsByFullName(fullName);
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
