package com.example.transportation_service.repository;

import com.example.transportation_service.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    //language=PostgreSQL
    public static final String ADD_USER = """
            INSERT INTO users (full_name, username, password, role)
            VALUES (:fullName, :username, :password, :role)
            """;

    @Transactional
    public int registrationUser(UserCreateDto user) {
        Map<String, String> arguments = Map.of(
                "fullName", user.getFullName(),
                "username", user.getUsername(),
                "password", user.getPassword(),
                "role", user.getRole().toString()
        );
        return jdbcTemplate.update(ADD_USER, arguments);
    }
}
