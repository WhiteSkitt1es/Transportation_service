package com.example.transportation_service.mapper;


import com.example.transportation_service.dto.Role;
import com.example.transportation_service.dto.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<Users> {

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        String fullName = rs.getString("full_name");
        String username = rs.getString("username");
        String password = rs.getString("password");
        Role role = rs.getObject("role", Role.class);

        return new Users(fullName, username, password, role);
    }
}
