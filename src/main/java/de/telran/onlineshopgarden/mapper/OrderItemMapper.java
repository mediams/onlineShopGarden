package de.telran.onlineshopgarden.mapper;

import de.telran.onlineshopgarden.dto.OrderItemDto;
import de.telran.onlineshopgarden.dto.OrderItemFullDto;
import de.telran.onlineshopgarden.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemDto entityToDto(OrderItem entity);

    @Mapping(target = "orderId", source = "order.orderId")
    OrderItemFullDto entityToFullDto(OrderItem entity);

    List<OrderItemFullDto> entityListToFullDtoList(List<OrderItem> entities);
}
