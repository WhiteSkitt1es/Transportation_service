package com.example.transportation_service.repository;

import com.example.transportation_service.dto.CarrierDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CarrierRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    //language=PostgreSQL
    public static final String CREATE_CARRIER = """
            INSERT INTO carriers (name, phone_number)
            VALUES (:name, :phoneNumber)
            """;

    public static final String UPDATE_CARRIER = """
            UPDATE carriers
            SET name = :name,
                phone_number = :phoneNumber
            WHERE id = :id
            """;

    public static final String DELETE_CARRIER = """
            DELETE FROM carriers
            WHERE id = :id
            """;

    @Transactional
    public void createCarrier(CarrierDto carrierDto) {
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", carrierDto.getName())
                .addValue("phoneNumber",carrierDto.getTelephoneNumber());

        jdbcTemplate.update(CREATE_CARRIER, param);
    }

    @Transactional
    public int updateCarrier(Long id, CarrierDto carrierDto) {
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", carrierDto.getName())
                .addValue("phoneNumber", carrierDto.getTelephoneNumber());

        return jdbcTemplate.update(UPDATE_CARRIER, param);
    }

    @Transactional
    public int deleteCarrier(Long id) {
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);

        return jdbcTemplate.update(DELETE_CARRIER,param);
    }
}
