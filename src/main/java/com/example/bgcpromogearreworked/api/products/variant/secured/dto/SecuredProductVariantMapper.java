package com.example.bgcpromogearreworked.api.products.variant.secured.dto;

import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import com.example.bgcpromogearreworked.persistence.repositories.OptionValueRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SecuredProductVariantMapper {

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Mapping(source = "imageId", target = "image.id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "optionValueIds", target = "optionValues")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "globalInventoryLevel", ignore = true)
    public abstract ProductVariant fromCreate(SecuredProductVariantCreate productVariantCreate);

    @Mapping(source = "imageId", target = "image.id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "optionValueIds", target = "optionValues")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "globalInventoryLevel", ignore = true)
    public abstract ProductVariant fromUpdate(SecuredProductVariantUpdate productVariantUpdate,
                                              @MappingTarget ProductVariant productVariant);

    @Mapping(source = "imageId", target = "image.id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "optionValueIds", target = "optionValues")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "globalInventoryLevel", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract ProductVariant fromPartialUpdate(SecuredProductVariantPartialUpdate productVariantPartialUpdate,
                                                     @MappingTarget ProductVariant productVariant);

    @Mapping(source = "optionValues", target = "options")
    public abstract SecuredProductVariantResponse toResponse(ProductVariant productVariant);

    public SecuredProductVariantBatchResponse toBatchResponse(List<ProductVariant> productVariants) {
        return new SecuredProductVariantBatchResponse(productVariants.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @AfterMapping
    protected void setImageNullIfImageIdNull(@MappingTarget ProductVariant productVariant) {
        if (productVariant.getImage().getId() == null) {
            productVariant.setImage(null);
        }
    }

    protected OptionValue map(Long optionValueId) {
        return optionValueRepo.findById(optionValueId).orElseThrow();
    }

    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract SecuredProductVariantResponse.NestedOptionValue map(OptionValue optionValue);

}
