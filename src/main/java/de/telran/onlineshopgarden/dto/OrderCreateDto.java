package de.telran.onlineshopgarden.dto;

import de.telran.onlineshopgarden.entity.enums.DeliveryMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "{validation.order.itemsNotNull}")
    @Size(min = 1, message = "{validation.order.itemsSize}")
    private List<OrderItemDto> items;

    @NotNull(message = "{validation.order.deliveryAddressNotNull}")
    @Size(min = 5, max = 100, message = "{validation.order.deliveryAddressSize}")
    private String deliveryAddress;

    @NotNull(message = "{validation.order.deliveryMethodNotNull}")
    private DeliveryMethod deliveryMethod;

    @NotBlank(message = "{validation.order.contactPhoneNotBlank}")
    @Pattern(regexp = "^\\+?[0-9\\- ]{7,20}$", message = "{validation.order.contactPhonePattern}")
    private String contactPhone;
}
