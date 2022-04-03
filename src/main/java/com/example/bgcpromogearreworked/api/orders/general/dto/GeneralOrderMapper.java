package com.example.bgcpromogearreworked.api.orders.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.CartItem;
import com.example.bgcpromogearreworked.persistence.entities.OfficeLocation;
import com.example.bgcpromogearreworked.persistence.entities.Order;
import com.example.bgcpromogearreworked.persistence.entities.User;
import com.example.bgcpromogearreworked.persistence.repositories.OfficeLocationRepository;
import com.example.bgcpromogearreworked.persistence.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Mapping(source = "items", target = "orderItems")
    @Mapping(target = "fulfillerComments", ignore = true)
    @Mapping(target = "fulfiller", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "completedDate", ignore = true)
    @Mapping(target = "owedCredit", ignore = true)
    @Mapping(target = "extraInfo.orderId", ignore = true)
    @Mapping(target = "extraInfo.order", ignore = true)
    @Mapping(target = "orderItems[].orderId", ignore = true)
    @Mapping(target = "orderItems[].order", ignore = true)
    public abstract Order fromCreate(GeneralOrderCreate orderCreate);

    @Mapping(source = "submitter.displayName", target = "submitter")
    @Mapping(source = "fulfiller.displayName", target = "fulfiller")
    @Mapping(source = "recipient.displayName", target = "recipient")
    public abstract GeneralOrderResponse toResponse(Order order);

    public GeneralOrderBatchResponse toBatchResponse(List<Order> orders) {
        return new GeneralOrderBatchResponse(orders.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @Mapping(source = "variant.product.price", target = "price")
    public abstract List<GeneralOrderCreate.NestedOrderItem> cartItemsToOrderItems(List<CartItem> cartItems);

    protected User userFromId(Long id) {
        return userRepo.getById(id);
    }

    protected OfficeLocation officeLocationFromId(Long id) {
        return locationRepo.getById(id);
    }

}
