package com.example.bgcpromogearreworked.api.orders.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.CartItem;
import com.example.bgcpromogearreworked.persistence.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class GeneralOrderMapper {

    @Mapping(source = "submitter.displayName", target = "submitter")
    @Mapping(source = "fulfiller.displayName", target = "fulfiller")
    @Mapping(source = "recipient.displayName", target = "recipient")
    public abstract GeneralOrderResponse toResponse(Order order);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Order fromCreate(GeneralOrderCreate orderCreate);

    public abstract List<GeneralOrderCreate.NestedOrderItem> map(List<CartItem> cartItems);
// TODO: 2022-03-27 finish implement
}
