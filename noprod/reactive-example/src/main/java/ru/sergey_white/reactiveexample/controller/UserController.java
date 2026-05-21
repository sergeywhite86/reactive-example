package ru.sergey_white.reactiveexample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sergey_white.reactiveexample.model.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.sergey_white.reactiveexample.service.UserService;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "CRUD operations for users")
public class UserController {
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all users", description = "Returns a list of all users")
    public Flux<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Returns a single user")
    public Mono<UserDto> getUserById(
            @Parameter(description = "User ID") @PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new user", description = "Creates a user and returns the created user (without ID)")
    public Mono<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing user", description = "Updates user by ID")
    public Mono<UserDto> updateUser(
            @Parameter(description = "User ID") @PathVariable Long id,
            @Valid @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete user", description = "Deletes user by ID")
    public Mono<Void> deleteUser(@Parameter(description = "User ID") @PathVariable Long id) {
        return userService.deleteUser(id);
    }

}