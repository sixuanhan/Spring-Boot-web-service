package com.example.userRegistration;

import java.net.URI;
import java.net.URISyntaxException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService2 userService;
    private CountryService countryService;

    @Operation(summary = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) })})
    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User user) throws URISyntaxException {
        User createdUser = userService.create(user);
        createdUser.setPopulation(countryService.getPopulationByCountry(createdUser.getCountry()));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(createdUser);
    }


    @Operation(summary = "Get a user by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<User> read(@PathVariable("id") Long id) {
        User foundUser = userService.read(id);
        if (foundUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundUser);
        }
    }

    @Operation(summary = "Update a user by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) {
        User updatedUser = userService.update(id, user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedUser);
        }
    }

    @Operation(summary = "delete a user by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted the user",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
