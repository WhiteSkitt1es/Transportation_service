package com.example.transportation_service.service;

import com.example.transportation_service.dto.PersonalTicketDto;
import com.example.transportation_service.dto.TicketFilter;
import com.example.transportation_service.dto.UserCreateDto;
import com.example.transportation_service.repository.FilterTicketRepository;
import com.example.transportation_service.repository.TicketRepository;
import com.example.transportation_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportationService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final FilterTicketRepository filterTicketRepository;

    public int createUser(UserCreateDto userCreateDto) {
        return userRepository.registrationUser(userCreateDto);
    }

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

    public int buyTicket(Long id, String fullName) {
        return ticketRepository.buyTicket(id, fullName);
    }

    public List<PersonalTicketDto> findAllByTicketsByFullName(String fullName) {
        return ticketRepository.findAllTicketsByFullName(fullName);
    }
}
