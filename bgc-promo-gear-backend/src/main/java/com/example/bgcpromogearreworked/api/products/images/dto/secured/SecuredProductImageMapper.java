package com.example.bgcpromogearreworked.api.products.images.dto.secured;

import com.example.bgcpromogearreworked.persistence.entities.ProductImage;
import org.mapstruct.*;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class SecuredProductImageMapper {

    @Mapping(source = "productImageCreate.productId", target = "product.id")
    @Mapping(target = "productVariants", ignore = true)
    public abstract ProductImage fromCreate(SecuredProductImageCreate productImageCreate, String src);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "src", ignore = true)
    @Mapping(target = "productVariants", ignore = true)
    public abstract ProductImage fromUpdate(SecuredProductImageUpdate productImageUpdate,
                                            @MappingTarget ProductImage image);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "src", ignore = true)
    @Mapping(target = "productVariants", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract ProductImage fromPartialUpdate(SecuredProductImagePartialUpdate productImagePartialUpdate,
                                                   @MappingTarget ProductImage image);

    @Mapping(source = "product.id", target = "productId")
    public abstract SecuredProductImageResponse toResponse(ProductImage image);

    public SecuredProductImageBatchResponse toBatchResponse(Iterable<ProductImage> images) {
        return new SecuredProductImageBatchResponse(Streamable.of(images).map(this::toResponse).toList());
    }

    @AfterMapping
    protected void setProductToNullIfProductIdNull(@MappingTarget ProductImage image) {
        if (image == null) {
            return;
        }
        if (image.getProduct().getId() == null) {
            image.setProduct(null);
        }
    }

}
