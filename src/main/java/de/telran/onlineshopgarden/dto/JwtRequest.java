package de.telran.onlineshopgarden.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {

    @NotBlank(message = "{validation.user.emailNotBlank}")
    @Email(regexp = "^[a-zA-Z][\\w.-]*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{validation.user.email}")
    private String login;

    @NotBlank(message = "{validation.user.passwordNotBlank}")
    @Size(min = 8, message = "{validation.user.passwordSize}")
    private String password;

}