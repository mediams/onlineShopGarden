package de.telran.onlineshopgarden.mapper;

import de.telran.onlineshopgarden.dto.OrderItemDto;
import de.telran.onlineshopgarden.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {


    @Mapping(target = "priceAtPurchase", ignore = true)
    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem dtoToEntity(OrderItemDto dto);

    List<OrderItem> dtoListToEntity(List<OrderItemDto> entities);
}
