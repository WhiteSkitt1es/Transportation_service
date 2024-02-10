package com.example.transportation_service.repository;

import com.example.transportation_service.dto.PersonalTicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FilterTicketRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    //language=PostgreSQL
    public static final String FIND_BY_DATE_AND_POINT_AND_NAME_CARRIER = """
            SELECT * FROM tickets
            JOIN public.routes r ON r.id = tickets.routes_id
            JOIN public.carriers c ON c.id = r.id_carrier
            WHERE passenger_name IS NOT NULL OR (:fromTime IS NULL OR time >= :fromTime)
            OR (:toTime IS NULL OR time <= :toTime)
            OR (:departurePoint IS NULL OR r.departure_point = :departurePoint)
            OR (:destinationPoint IS NULL OR r.destination_point = :destinationPoint)
            OR (:name IS NULL OR c.name = :name)
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
        Map<String, String> arguments = Map.of(
                "fromTime", fromTime.toString(),
                "toTime", toTime.toString(),
                "departurePoint", departurePoint,
                "destinationPoint", destinationPoint,
                "name", name,
                "offset", offset.toString(),
                "limit", limit.toString()
        );
        return jdbcTemplate.query(FIND_BY_DATE_AND_POINT_AND_NAME_CARRIER, arguments, (rs, rowNum) -> new PersonalTicketDto(
                rs.getInt("seat_number"),
                rs.getObject("time", LocalDateTime.class),
                rs.getBigDecimal("price"),
                rs.getString("passenger_name"),
                rs.getString("r.departure_point"),
                rs.getString("r.destination_point")
        ));
    }
}
