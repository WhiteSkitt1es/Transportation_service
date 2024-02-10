package com.example.transportation_service.repository;

import com.example.transportation_service.dto.PersonalTicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketRepository {

    private final JdbcTemplate jdbcTemplate;

    //language=PostgreSQL
    public static final String FIND_ALL_TICKETS_BY_FULL_NAME = """
            SELECT seat_number, time, price, passenger_name, r.departure_point, r.destination_point
            FROM tickets
            join public.routes r on r.id = tickets.routes_id
            WHERE passenger_name = ?
            """;

    //language=PostgreSQL
    public static final String BUY_TICKET = """
            UPDATE tickets
            SET passenger_name = ?
            WHERE passenger_name IS NULL AND id = ?
            """;

    @Transactional
    public List<PersonalTicketDto> findAllTicketsByFullName (String fullName) {
        return jdbcTemplate.query(FIND_ALL_TICKETS_BY_FULL_NAME, (rs, rowNum) -> new PersonalTicketDto(
                rs.getInt("seat_number"),
                rs.getObject("time", LocalDateTime.class),
                rs.getBigDecimal("price"),
                rs.getString("passenger_name"),
                rs.getString("departure_point"),
                rs.getString("destination_point")
        ), fullName);
    }

    @Transactional
    public int buyTicket(Long id, String passengerName) {
        return  jdbcTemplate.update(BUY_TICKET, passengerName, id);
    }
}
