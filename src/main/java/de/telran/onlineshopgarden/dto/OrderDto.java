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
    @Pattern(regexp = "^(?!\\s)(?!.*\\s{2,})[A-ZÄÖÜЁА-Я][\\p{L}0-9.,'\\-\\s]{4,99}$", message = "{validation.order.deliveryAddressPattern}")
    private String deliveryAddress;

    @NotBlank(message = "{validation.order.contactPhoneNotBlank}")
    @Pattern(regexp = "^(?!\\s)(?!.*\\s{2,})\\+?[0-9][0-9\\- ]{6,19}$", message = "{validation.order.contactPhonePattern}")
    private String contactPhone;

    private DeliveryMethod deliveryMethod;

    private OrderStatus status;

    @NotNull(message = "{validation.order.itemsNotNull}")
    @Size(min = 1, message = "{validation.order.itemsSize}")
    private List<OrderItemDto> items;
}
