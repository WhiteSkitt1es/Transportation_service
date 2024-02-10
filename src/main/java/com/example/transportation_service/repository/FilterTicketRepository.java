package com.example.transportation_service.repository;

import com.example.transportation_service.dto.PersonalTicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FilterTicketRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    //language=PostgreSQL
    public static final String FIND_BY_DATE_AND_POINT_AND_NAME_CARRIER = """
            SELECT seat_number, time, price, passenger_name, r.departure_point, r.destination_point
            FROM tickets
            JOIN public.routes r ON r.id = tickets.routes_id
            JOIN public.carriers c ON c.id = r.id_carrier
            WHERE passenger_name IS NOT NULL AND (cast(:fromTime as timestamp) IS NULL OR time >= :fromTime)
            AND (cast(:toTime as timestamp) IS NULL OR time <= :toTime)
            AND (cast(:departurePoint as varchar) IS NULL OR r.departure_point = :departurePoint)
            AND (cast(:destinationPoint as varchar) IS NULL OR r.destination_point = :destinationPoint)
            AND (cast(:name as varchar) IS NULL OR c.name = :name)
            ORDER BY tickets.id
            LIMIT :limit
            OFFSET :offset
            """;

    @Transactional
    public List<PersonalTicketDto> getAll(LocalDateTime fromTime,
                                          LocalDateTime toTime,
                                          String departurePoint,
                                          String destinationPoint,
                                          String name,
                                          Integer offset,
                                          Integer limit) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("fromTime", fromTime)
                .addValue("toTime", toTime)
                .addValue("departurePoint", departurePoint)
                .addValue("destinationPoint", destinationPoint)
                .addValue("name", name)
                .addValue("offset", offset)
                .addValue("limit", limit);

        return jdbcTemplate.query(FIND_BY_DATE_AND_POINT_AND_NAME_CARRIER, parameterSource, (rs, rowNum) -> new PersonalTicketDto(
                rs.getInt("seat_number"),
                rs.getObject("time", LocalDateTime.class),
                rs.getBigDecimal("price"),
                rs.getString("passenger_name"),
                rs.getString("departure_point"),
                rs.getString("destination_point")
        ));
    }
}
