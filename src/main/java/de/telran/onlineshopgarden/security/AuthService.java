package de.telran.onlineshopgarden.security;

import de.telran.onlineshopgarden.dto.JwtRequest;
import de.telran.onlineshopgarden.dto.JwtResponse;
import de.telran.onlineshopgarden.entity.User;
import de.telran.onlineshopgarden.exception.ResourceNotFoundException;
import de.telran.onlineshopgarden.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
        final User user = userRepository.findUserByEmail(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("User is not found"));
        if (encoder.matches(authRequest.getPassword(), user.getPasswordHash())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            user.setRefreshToken(refreshToken);
            userRepository.save(user);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Wrong password");
        }
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final User user = userRepository.findUserByEmail(login)
                    .orElseThrow(() -> new AuthException("User is not found"));
            final String savedRefreshToken = user.getRefreshToken();
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        throw new AuthException("Token is not valid");
    }

    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final User user = userRepository.findUserByEmail(login)
                    .orElseThrow(() -> new AuthException("User is not found"));
            final String savedRefreshToken = user.getRefreshToken();
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                user.setRefreshToken(newRefreshToken);
                userRepository.save(user);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid JWT token");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    public User getCurrentUser() {
        JwtAuthentication authInfo = getAuthInfo();
        String login = authInfo.getLogin();
        return userRepository.findUserByEmail(login)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + login + " not found"));
    }

    public boolean isCurrentUserAdmin() {
        return getAuthInfo().getRoles().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMINISTRATOR"));
    }
}