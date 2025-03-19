package de.telran.onlineshopgarden.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    @NotBlank(message = "Name must not be blank")
    @Size(min = 2, max = 50, message = "Name length must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Phone number must not be blank")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid phone number format")
    private String phone;
}
