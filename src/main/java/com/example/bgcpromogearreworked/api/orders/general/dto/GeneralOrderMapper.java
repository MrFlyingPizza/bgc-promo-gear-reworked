package com.example.bgcpromogearreworked.api.orders.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.*;
import com.example.bgcpromogearreworked.persistence.repositories.OfficeLocationRepository;
import com.example.bgcpromogearreworked.persistence.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralOrderMapper {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OfficeLocationRepository locationRepo;

    @Mapping(source = "submitterId", target = "submitter")
    @Mapping(source = "recipientId", target = "recipient")
    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "comments", target = "submitterComments")
    @Mapping(target = "fulfillerComments", ignore = true)
    @Mapping(target = "fulfiller", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "completedDate", ignore = true)
    @Mapping(target = "extraInfo", ignore = true)
    @Mapping(target = "items[].order", ignore = true)
    @Mapping(target = "items[].variant", ignore = true)
    @Mapping(target = "items[].orderId", ignore = true)
    public abstract Order fromCreate(GeneralOrderCreate orderCreate);

    @Mapping(source = "submitter.displayName", target = "submitter")
    @Mapping(source = "fulfiller.displayName", target = "fulfiller")
    @Mapping(source = "recipient.displayName", target = "recipient")
    @Transactional
    public abstract GeneralOrderResponse toResponse(Order order);

    @Transactional
    public GeneralOrderBatchResponse toBatchResponse(List<Order> orders) {
        return new GeneralOrderBatchResponse(orders.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @Transactional
    public abstract List<GeneralOrderCreate.NestedOrderItem> cartItemsToOrderItems(List<CartItem> cartItems);

    @Mapping(source = "variant.product.price", target = "price")
    protected abstract GeneralOrderCreate.NestedOrderItem map(CartItem cartItem);

    @Mapping(source = "variant.product", target = "product")
    @Transactional
    protected abstract GeneralOrderResponse.NestedOrderItem map(OrderItem item);

    @Mapping(source = "id", target = "valueId")
    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract GeneralOrderResponse.NestedOrderItem.NestedVariant.NestedOptionValue map(OptionValue optionValue);

    protected User userFromId(Long id) {
        return userRepo.getById(id);
    }

    protected OfficeLocation officeLocationFromId(Long id) {
        return locationRepo.getById(id);
    }

}
