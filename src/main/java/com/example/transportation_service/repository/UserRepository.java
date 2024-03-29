package com.example.transportation_service.repository;

import com.example.transportation_service.dto.UserCreateDto;
import com.example.transportation_service.dto.Users;
import com.example.transportation_service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    //language=PostgreSQL
    public static final String ADD_USER = """
            INSERT INTO users (full_name, username, password, role)
            VALUES (:fullName, :username, :password, :role)
            """;

    public static final String FIND_USER_BY_USERNAME = """
            SELECT full_name, username, password, role FROM users
            WHERE username = :username
            """;

    @Transactional
    public void registrationUser(UserCreateDto user) {
        Map<String, String> arguments = Map.of(
                "fullName", user.getFullName(),
                "username", user.getUsername(),
                "password", user.getPassword(),
                "role", user.getRole().toString()
        );
        jdbcTemplate.update(ADD_USER, arguments);
    }

    public Optional<Users> findUserByUsername(String username) {
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("username", username);

        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_USER_BY_USERNAME, param, new UserMapper()));
    }
}
