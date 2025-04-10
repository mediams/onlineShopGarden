package de.telran.onlineshopgarden.controller.api;

import de.telran.onlineshopgarden.dto.CartDto;
import de.telran.onlineshopgarden.dto.CartItemAddDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Cart Controller", description = "REST API for managing cart in the app")
public interface CartControllerApi {

    @Operation(summary = "Get cart for the currently authenticated user",
            description = "Retrieves the shopping cart of the currently authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND")
    })
    ResponseEntity<CartDto> getByUserId();

    @Operation(summary = "Add item to cart for the currently authenticated user",
            description = "Adds an item to the authenticated user's shopping cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN")
    })
    ResponseEntity<Void> addItem(@Valid @RequestBody CartItemAddDto dto);

    @Operation(summary = "Remove cart by user id for the currently authenticated user",
            description = "Deletes the shopping cart of the currently authenticated user with all items.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN")
    })
    ResponseEntity<Void> deleteByUserId();
}
