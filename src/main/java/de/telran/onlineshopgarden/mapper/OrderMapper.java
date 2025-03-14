package de.telran.onlineshopgarden.mapper;

import de.telran.onlineshopgarden.dto.OrderDto;
import de.telran.onlineshopgarden.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;


@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "deliveryAddress", source = "deliveryAddress")
    @Mapping(target = "contactPhone", source = "contactPhone")
    @Mapping(target = "deliveryMethod", source = "deliveryMethod")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    OrderDto entityToDto(Order order);

    List<OrderDto> entityListToDtoList(List<Order> orders);
}
