package de.telran.onlineshopgarden.dto;

import de.telran.onlineshopgarden.entity.enums.DeliveryMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto {

    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    private List<OrderItemDto> items;

    @NotBlank(message = "Delivery address must not be blank")
    private String deliveryAddress;

    @NotNull(message = "Delivery method must not be null")
    private DeliveryMethod deliveryMethod;

    @NotBlank(message = "Contact phone must not be blank")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid contact phone format")
    private String contactPhone;
}
