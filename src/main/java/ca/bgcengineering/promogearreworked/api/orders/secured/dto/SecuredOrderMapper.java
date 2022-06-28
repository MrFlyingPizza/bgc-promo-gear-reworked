package ca.bgcengineering.promogearreworked.api.orders.secured.dto;

import ca.bgcengineering.promogearreworked.api.inventorylevels.inventorylevel.secured.dto.SecuredInventoryLevelBatchResponse;
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
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

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

    @Mapping(source = "recipientId", target = "recipient")
    @Mapping(source = "fulfillerId", target = "fulfiller")
    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "submitterComments", ignore = true)
    @Mapping(target = "submitter", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "extraInfo", ignore = true)
    @Mapping(target = "completedDate", ignore = true)
    public abstract Order fromPartialUpdate(SecuredOrderPartialUpdate orderPartialUpdate, @MappingTarget Order order);

    @Mapping(source = "recipientId", target = "recipient")
    @Mapping(source = "fulfillerId", target = "fulfiller")
    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "submitterComments", ignore = true)
    @Mapping(target = "submitter", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "extraInfo", ignore = true)
    @Mapping(target = "completedDate", ignore = true)
    public abstract Order fromUpdate(SecuredOrderUpdate orderUpdate, @MappingTarget Order order);

    public abstract SecuredOrderResponse toResponse(Order order);

    public SecuredOrderBatchResponse toBatchResponse(Page<Order> page) {
        return new SecuredOrderBatchResponse(page.getContent().stream().map(this::toResponse).collect(Collectors.toList()),
                page.getTotalPages(),
                page.getNumber(),
                page.isFirst(),
                page.isLast(),
                page.getNumberOfElements(),
                page.getTotalElements(),
                page.getSort().isSorted());
    }

    @Mapping(source = "variantId", target = "variant")
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    protected abstract OrderItem map(ca.bgcengineering.promogearreworked.api.orders.secured.dto.SecuredOrderCreate.NestedOrderItem item);

    @Mapping(source = "id", target = "valueId")
    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract SecuredOrderResponse.NestedOrderItem.NestedProductVariant.NestedOptionValue map(OptionValue optionValue);

    @Mapping(source = "variant.product", target = "product")
    @Mapping(source = "variant.optionValues", target = "variant.options")
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
