package de.telran.onlineshopgarden.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "{validation.category.name}")
    @Pattern(regexp = "^[A-Z][A-Za-z -]{3,100}$", message = "{validation.category.nameSize}")
    private String category;

    @NotBlank(message = "{validation.category.imageUrlNotBlank}")
    @Pattern(regexp = "^(https?://)?([\\w\\d.-]+)\\.([a-z]{2,6}\\.?)([/\\w\\d.-]*)*/?$",
            message = "{validation.category.imageUrlNotValid}")
    private String imageUrl;
}
