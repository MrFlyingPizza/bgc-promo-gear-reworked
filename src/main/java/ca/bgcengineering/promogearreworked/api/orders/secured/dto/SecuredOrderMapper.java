package ca.bgcengineering.promogearreworked.api.orders.secured.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.*;
import ca.bgcengineering.promogearreworked.persistence.repositories.OfficeLocationRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductVariantRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.UserRepository;
import org.aspectj.lang.annotation.After;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class SecuredOrderMapper {

    @Autowired
    private ProductVariantRepository variantRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OfficeLocationRepository locationRepo;

    @Mapping(source = "recipientId", target = "recipient")
    @Mapping(source = "fulfillerId", target = "fulfiller")
    @Mapping(source = "submitterId", target = "submitter")
    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completedDate", ignore = true)
    @Mapping(target = "extraInfo.orderId", ignore = true)
    @Mapping(target = "extraInfo.order", ignore = true)
    public abstract Order fromCreate(SecuredOrderCreate orderCreate);

    public abstract SecuredOrderResponse toResponse(Order order);

    @Mapping(source = "variantId", target = "variant")
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    protected abstract OrderItem map(ca.bgcengineering.promogearreworked.api.orders.secured.dto.SecuredOrderCreate.NestedOrderItem item);

    protected abstract SecuredOrderResponse.NestedOrderItem map(OrderItem item);

    @AfterMapping
    protected void mapPrice(@MappingTarget OrderItem item) {
        item.setPrice(item.getVariant().getProduct().getPrice());
    }

    protected ProductVariant mapVariantFromId(Long variantId) {
        return variantId == null || variantId == 0 ? null : variantRepo.getById(variantId);
    }

    protected OfficeLocation mapLocationFromId(Long locationId) {
        return locationId == null || locationId == 0 ? null : locationRepo.getById(locationId);
    }

    protected User mapUserFromId(Long userId) {
        return userId == null || userId == 0 ? null : userRepo.getById(userId);
    }

}
