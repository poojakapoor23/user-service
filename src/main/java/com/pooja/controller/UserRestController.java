package com.pooja.controller;

import com.pooja.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Operation(summary = "Create a new user", description = "Accepts a User JSON and returns success message")
    @ApiResponse(responseCode = "200", description = "User successfully created")
    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok("User Created: " + user.getName());
    }

    @Operation(summary = "Get user by ID", description = "Fetch a user based on the ID")
    @ApiResponse(responseCode = "200", description = "User fetched successfully")
    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable String id) {
        return ResponseEntity.ok("User fetched with ID: " + id);
    }

    @Operation(summary = "Update user by ID", description = "Update existing user details")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @Valid @RequestBody User user) {
        return ResponseEntity.ok("User Updated: " + user.getName());
    }

    @Operation(summary = "Delete user by ID", description = "Deletes a user based on the ID")
    @ApiResponse(responseCode = "200", description = "User deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok("User Deleted with ID: " + id);
    }
}
