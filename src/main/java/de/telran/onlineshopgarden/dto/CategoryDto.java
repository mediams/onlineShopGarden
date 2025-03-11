package de.telran.onlineshopgarden.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private String categoryId;

    @NotBlank(message = "Category name must not be empty")
    @Size(min = 3, max = 100, message = "Category name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "URL image must not be empty")
    @Pattern(regexp = "^(https?://)?([\\w\\d.-]+)\\.([a-z]{2,6}\\.?)([/\\w\\d.-]*)*/?$",
            message = "Invalid URL image format")
    private String imageUrl;
}
