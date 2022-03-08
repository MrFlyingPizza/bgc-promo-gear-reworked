package com.example.bgcpromogearreworked.api.products.product.dto.secured;

import com.example.bgcpromogearreworked.api.options.persistence.OptionValue;
import com.example.bgcpromogearreworked.api.products.persistence.Product;
import com.example.bgcpromogearreworked.api.products.persistence.ProductVariant;
import org.mapstruct.*;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class SecuredProductMapper {

    @Mapping(source = "categoryId", target = "category.id")
    public abstract Product fromCreate(SecuredProductCreate productCreate);

    @Mapping(source = "categoryId", target = "category.id")
    public abstract Product fromUpdate(SecuredProductUpdate productUpdate, @MappingTarget Product targetProduct);

    @Mapping(source = "categoryId", target = "category.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Product fromPartialUpdate(SecuredProductPartialUpdate productPartialUpdate, @MappingTarget Product targetProduct);

    @Mapping(source = "category.parent.id", target = "category.parentId")
    @Mapping(source = "category.parent.name", target = "category.parentName")
    public abstract SecuredProductResponse toResponse(Product product);

    @Mapping(source = "optionValues", target = "options")
    protected abstract SecuredProductResponse.NestedProductVariant map(ProductVariant productVariant);

    @Mapping(source = "value", target = ".")
    protected abstract String map(OptionValue optionValue);

    public SecuredProductBatchResponse toBatchResponse(Iterable<Product> products) {
        return new SecuredProductBatchResponse(Streamable.of(products).map(this::toResponse).toList());
    }

    @AfterMapping
    public void setParentNullIfNoParentId(@MappingTarget Product product) {
        if (product.getCategory() == null) {
            return;
        }
        if (product.getCategory().getId() == null) {
            product.setCategory(null);
        }
    }
}
