package de.telran.onlineshopgarden.dto;

import de.telran.onlineshopgarden.entity.enums.DeliveryMethod;
import de.telran.onlineshopgarden.entity.enums.OrderStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String orderId;
    private String userId;
    private String deliveryAddress;
    private String contactPhone;
    private DeliveryMethod deliveryMethod;
    private OrderStatus status;
}
