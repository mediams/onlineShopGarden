package de.telran.onlineshopgarden.dto;

import de.telran.onlineshopgarden.entity.enums.DeliveryMethod;
import de.telran.onlineshopgarden.entity.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String orderId;

    private String userId;

    @NotNull(message = "{validation.order.deliveryAddressNotNull}")
    @Size(min = 5, max = 100, message = "{validation.order.deliveryAddressSize}")
    private String deliveryAddress;

    @NotBlank(message = "{validation.order.contactPhoneNotBlank}")
    @Pattern(regexp = "^\\+?[0-9\\- ]{7,20}$", message = "{validation.order.contactPhonePattern}")
    private String contactPhone;

    @NotNull(message = "{validation.order.deliveryMethodNotNull}")
    private DeliveryMethod deliveryMethod;

    @NotNull(message = "{validation.order.statusNotNull}")
    private OrderStatus status;

    @NotNull(message = "{validation.order.itemsNotNull}")
    @Size(min = 1, message = "{validation.order.itemsSize}")
    private List<OrderItemDto> items;
}
