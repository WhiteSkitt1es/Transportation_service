package com.example.transportation_service.repository;

import com.example.transportation_service.dto.PersonalTicketDto;
import com.example.transportation_service.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TicketRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=PostgreSQL
    public static final String FIND_ALL_TICKETS_BY_FULL_NAME = """
            SELECT seat_number, time, price, passenger_name, r.departure_point, r.destination_point
            FROM tickets
            join public.routes r on r.id = tickets.routes_id
            WHERE passenger_name = ?
            """;

    public static final String BUY_TICKET = """
            UPDATE tickets
            SET passenger_name = ?
            WHERE passenger_name IS NULL AND id = ?
            """;

    public static final String CREATE_TICKET = """
            INSERT INTO tickets (seat_number, time, price, routes_id)
            VALUES (:seatNumber, :time, :price, :routesId)
            """;

    public static final String UPDATE_TICKET = """
            UPDATE tickets
            SET seat_number = :seatNumber,
                time = :time,
                price = :price,
                routes_id = :routesId
            WHERE id = :id
            """;

    public static final String DELETE_TICKET = """
            DELETE FROM tickets
            WHERE id = :id
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

    @Transactional
    public void createTicket(TicketDto ticketDto) {
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("seatNumber", ticketDto.getSeatNumber())
                .addValue("time", ticketDto.getTime())
                .addValue("price", ticketDto.getPrice())
                .addValue("routesId", ticketDto.getRoutesId());

        namedParameterJdbcTemplate.update(CREATE_TICKET, param);
    }

    @Transactional
    public int updateTicket(Long id, TicketDto ticketDto) {
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("seatNumber", ticketDto.getSeatNumber())
                .addValue("time", ticketDto.getTime())
                .addValue("price", ticketDto.getPrice())
                .addValue("routesId", ticketDto.getRoutesId());

        return namedParameterJdbcTemplate.update(UPDATE_TICKET, param);
    }

    @Transactional
    public int deleteTicket(Long id) {
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);

        return namedParameterJdbcTemplate.update(DELETE_TICKET, param);
    }
}
