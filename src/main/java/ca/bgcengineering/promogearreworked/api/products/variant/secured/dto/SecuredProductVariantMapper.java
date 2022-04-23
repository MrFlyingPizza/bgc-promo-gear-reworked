package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.OptionValue;
import ca.bgcengineering.promogearreworked.persistence.entities.Product;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductImage;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductImageRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SecuredProductVariantMapper {

    @Autowired
    private OptionValueRepository optionValueRepo;

    @Autowired
    private ProductImageRepository imageRepo;

    @Autowired
    private ProductRepository productRepo;

    @Mapping(source = "imageId", target = "image")
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "optionValueIds", target = "optionValues")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "globalInventoryLevel", ignore = true)
    public abstract ProductVariant fromCreate(SecuredProductVariantCreate productVariantCreate);

    @Mapping(source = "imageId", target = "image")
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "optionValueIds", target = "optionValues")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "globalInventoryLevel", ignore = true)
    public abstract ProductVariant fromUpdate(SecuredProductVariantUpdate productVariantUpdate,
                                              @MappingTarget ProductVariant productVariant);

    @Mapping(source = "imageId", target = "image")
    @Mapping(source = "productId", target = "product")
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

    protected ProductImage mapProductImageFromId(Long imageId) {
        return imageId == null || imageId == 0 ? null : imageRepo.findById(imageId).orElseThrow();
    }

    protected Product mapProductFromId(Long productId) {
        return productId == null || productId == 0 ? null : productRepo.findById(productId).orElseThrow();
    }

    protected OptionValue map(Long optionValueId) {
        return optionValueRepo.findById(optionValueId).orElseThrow();
    }

    @Mapping(source = "id", target = "valueId")
    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract SecuredProductVariantResponse.NestedOptionValue map(OptionValue optionValue);

}
