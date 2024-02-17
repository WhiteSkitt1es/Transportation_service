package com.example.transportation_service.http.rest;

import com.example.transportation_service.dto.UserCreateDto;
import com.example.transportation_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "REST контроллер для создания пользователя")
public class UserRestController {

    private final UserService userService;

    @Operation(
            description = "Создание нового пользователя",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201"
                    )
            }
    )
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Validated UserCreateDto user) {
        userService.createUser(user);
    }
}
