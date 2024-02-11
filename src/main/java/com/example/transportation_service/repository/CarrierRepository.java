package com.example.transportation_service.repository;

import com.example.transportation_service.dto.CarrierCreateDto;
import lombok.RequiredArgsConstructor;
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
    public int createCarrier(CarrierCreateDto carrierCreateDto) {
        Map<String, String> param = Map.of(
                "name", carrierCreateDto.getName(),
                "phoneNumber", carrierCreateDto.getTelephoneNumber()
        );
        return jdbcTemplate.update(CREATE_CARRIER, param);
    }

    @Transactional
    public int updateCarrier(Long id, CarrierCreateDto carrierCreateDto) {
        Map<String, String> param = Map.of(
                "id", id.toString(),
                "name", carrierCreateDto.getName(),
                "phoneNumber", carrierCreateDto.getTelephoneNumber()
        );
        return jdbcTemplate.update(UPDATE_CARRIER, param);
    }

    @Transactional
    public int deleteCurrier(Long id) {
        Map<String, String> param = Map.of(
                "id", id.toString()
        );
        return jdbcTemplate.update(DELETE_CARRIER,param);
    }
}
