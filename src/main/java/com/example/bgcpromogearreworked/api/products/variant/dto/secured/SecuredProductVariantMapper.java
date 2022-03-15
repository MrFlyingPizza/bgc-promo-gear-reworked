package com.example.bgcpromogearreworked.api.products.variant.dto.secured;

import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import org.mapstruct.*;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class SecuredProductVariantMapper {

    // TODO: 2022-03-14 finish mapping, check build output warnings

    @Mapping(source = "imageId", target = "image.id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract ProductVariant fromCreate(SecuredProductVariantCreate productVariantCreate);

    @Mapping(source = "imageId", target = "image.id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    public abstract ProductVariant fromUpdate(SecuredProductVariantUpdate productVariantUpdate,
                                              @MappingTarget ProductVariant productVariant);

    @Mapping(source = "imageId", target = "image.id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract ProductVariant fromPartialUpdate(SecuredProductVariantPartialUpdate productVariantPartialUpdate,
                                                     @MappingTarget ProductVariant productVariant);

    public abstract SecuredProductVariantResponse toResponse(ProductVariant productVariant); // TODO: 2022-03-10 implement
    public SecuredProductVariantBatchResponse toBatchResponse(Iterable<ProductVariant> productVariants) {
        return new SecuredProductVariantBatchResponse(Streamable.of(productVariants).map(this::toResponse).toList());
    }

    @AfterMapping
    private void setImageNullIfImageIdNull(@MappingTarget ProductVariant productVariant) {
        if (productVariant.getImage().getId() == null) {
            productVariant.setImage(null);
        }
    }

}
