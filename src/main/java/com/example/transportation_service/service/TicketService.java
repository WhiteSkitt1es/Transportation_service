package com.example.transportation_service.service;

import com.example.transportation_service.dto.PersonalTicketDto;
import com.example.transportation_service.dto.TicketDto;
import com.example.transportation_service.dto.TicketFilter;
import com.example.transportation_service.repository.FilterTicketRepository;
import com.example.transportation_service.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final FilterTicketRepository filterTicketRepository;

    public List<PersonalTicketDto> findAll(Integer offset, Integer limit, TicketFilter filter) {
        return filterTicketRepository.getAll(
                filter.getFromTime(),
                filter.getToTime(),
                filter.getDeparturePoint(),
                filter.getDestinationPoint(),
                filter.getName(),
                offset,
                limit);
    }

    public boolean buyTicket(Long id, String fullName) {
        return ticketRepository.buyTicket(id, fullName) > 0;
    }

    public List<PersonalTicketDto> findAllByTicketsByFullName(String fullName) {
        return ticketRepository.findAllTicketsByFullName(fullName);
    }

    public void createTicket(TicketDto ticketDto) {
        ticketRepository.createTicket(ticketDto);
    }

    public boolean updateTicket(Long id, TicketDto ticketDto) {
        return ticketRepository.updateTicket(id, ticketDto) > 0;
    }

    public boolean deleteTicket(Long id) {
        return ticketRepository.deleteTicket(id) > 0;
    }
}
