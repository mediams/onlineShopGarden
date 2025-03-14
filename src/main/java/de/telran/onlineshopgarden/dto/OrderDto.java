package de.telran.onlineshopgarden.dto;
import de.telran.onlineshopgarden.entity.enums.DeliveryMethod;
import de.telran.onlineshopgarden.entity.enums.OrderStatus;
import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer orderId;
    private Integer userId;
    private String deliveryAddress;
    private String contactPhone;
    private DeliveryMethod deliveryMethod;
    private OrderStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
