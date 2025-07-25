package ca.bgcengineering.promogearreworked.api.products.product.secured.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.*;
import ca.bgcengineering.promogearreworked.persistence.repositories.CategoryRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SecuredProductMapper {

    @Autowired
    private OptionRepository optionRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "optionIds", target = "options")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "optionValues", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Product fromCreate(SecuredProductCreate productCreate);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "optionIds", target = "options")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "optionValues", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Product fromUpdate(SecuredProductUpdate productUpdate, @MappingTarget Product targetProduct);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "optionIds", target = "options")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "optionValues", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Product fromPartialUpdate(SecuredProductPartialUpdate productPartialUpdate, @MappingTarget Product targetProduct);

    public abstract SecuredProductResponse toResponse(Product product);

    public SecuredProductBatchResponse toBatchResponse(Page<Product> page) {
        return new SecuredProductBatchResponse(page.getContent().stream().map(this::toResponse).collect(Collectors.toList()),
                page.getTotalPages(),
                page.getNumber(),
                page.isFirst(),
                page.isLast(),
                page.getNumberOfElements(),
                page.getTotalElements(),
                page.getSort().isSorted());
    }

    @AfterMapping
    protected void setParentNullIfNoParentId(@MappingTarget Product product) {
        if (product.getCategory() == null) {
            return;
        }
        if (product.getCategory().getId() == null) {
            product.setCategory(null);
        }
    }

    @Mapping(source = "optionValues", target = "options")
    protected abstract SecuredProductResponse.NestedProductVariant map(ProductVariant productVariant);

    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "option.name", target = "name")
    protected abstract SecuredProductResponse.NestedProductVariant.NestedOptionValue map(OptionValue optionValue);

    protected Option mapOption(Long optionId) {
        return optionRepo.getById(optionId);
    }

    protected Category mapCategory(Long categoryId) {
        return categoryRepo.getById(categoryId);
    }

}
