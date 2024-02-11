package com.example.transportation_service.repository;

import com.example.transportation_service.dto.RouteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RouteRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    //language=PostgreSQL
    public static final String CREATE_ROUTE = """
            INSERT INTO routes (departure_point, destination_point, id_carrier)
            VALUES (:departurePoint, :destinationPoint, :idCarrier)
            """;
    public static final String UPDATE_ROUTE = """
            UPDATE routes
            SET departure_point = :departurePoint,
                destination_point = :destinationPoint,
                id_carrier = :idCarrier
            WHERE id = :id
            """;
    public static final String DELETE_ROUTE = """
            DELETE FROM routes
            WHERE id = :id;
            """;

    @Transactional
    public void createRoute(RouteDto routeDto) {
        Map<String, String> param = Map.of(
                "departurePoint", routeDto.getDeparturePoint(),
                "destinationPoints", routeDto.getDestinationPoint(),
                "idCarrier", routeDto.getIdCarrier().toString()
        );
        jdbcTemplate.update(CREATE_ROUTE, param);
    }

    @Transactional
    public int updateRoute(Long id, RouteDto routeDto) {
        Map<String, String> param = Map.of(
                "id", id.toString(),
                "departurePoint", routeDto.getDeparturePoint(),
                "destinationPoints", routeDto.getDestinationPoint(),
                "idCarrier", routeDto.getIdCarrier().toString()
        );
        return jdbcTemplate.update(UPDATE_ROUTE, param);
    }
    @Transactional
    public int deleteRoute(Long id) {
        Map<String, String> param = Map.of(
                "id", id.toString()
        );
        return jdbcTemplate.update(DELETE_ROUTE, param);
    }
}
