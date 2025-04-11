package de.telran.onlineshopgarden.controller.api;

import de.telran.onlineshopgarden.dto.JwtRequestRefresh;
import de.telran.onlineshopgarden.dto.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.security.auth.message.AuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth Controller", description = "Endpoints for retrieving new access and refresh tokens")
public interface AuthControllerApi {
    @Operation(summary = "Public endpoint - Get new access token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Access token issued successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid or expired refresh token"),
    })
    ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody JwtRequestRefresh request) throws AuthException;

    @Operation(summary = "Get new refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Access token issued successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid or expired refresh token"),
    })
    ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody JwtRequestRefresh request) throws AuthException;
}