package ca.bgcengineering.promogearreworked.api.orders.general.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.*;
import ca.bgcengineering.promogearreworked.persistence.repositories.OfficeLocationRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductVariantRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.UserRepository;
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

    @Autowired
    private ProductVariantRepository variantRepo;

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
    public abstract Order fromCreate(GeneralOrderCreate orderCreate);

    @Mapping(source = "submitter.displayName", target = "submitter")
    @Mapping(source = "fulfiller.displayName", target = "fulfiller")
    @Mapping(source = "recipient.displayName", target = "recipient")
    public abstract GeneralOrderResponse toResponse(Order order);

    public GeneralOrderBatchResponse toBatchResponse(List<Order> orders) {
        return new GeneralOrderBatchResponse(orders.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    public abstract List<GeneralOrderCreate.NestedOrderItem> cartItemsToOrderItems(List<CartItem> cartItems);

    @Mapping(source = "variant.product.price", target = "price")
    protected abstract GeneralOrderCreate.NestedOrderItem map(CartItem cartItem);

    @Mapping(source = "variantId", target = "variant")
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "order", ignore = true)
    protected abstract OrderItem map(GeneralOrderCreate.NestedOrderItem item);

    @Mapping(source = "variant.product", target = "product")
    @Mapping(source = "variant.optionValues", target = "variant.options")
    protected abstract GeneralOrderResponse.NestedOrderItem map(OrderItem item);

    @Mapping(source = "id", target = "valueId")
    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract GeneralOrderResponse.NestedOrderItem.NestedProductVariant.NestedOptionValue map(OptionValue optionValue);

    protected User userFromId(Long id) {
        return id == null || id == 0 ? null : userRepo.getById(id);
    }

    protected OfficeLocation officeLocationFromId(Long id) {
        return id == null || id == 0 ? null : locationRepo.getById(id);
    }

    protected ProductVariant productVariantFromId(Long id) {
        return id == null || id == 0 ? null : variantRepo.getById(id);
    }

}
