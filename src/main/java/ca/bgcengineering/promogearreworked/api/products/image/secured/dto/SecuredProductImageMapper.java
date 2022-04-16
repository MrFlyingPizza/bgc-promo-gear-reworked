package ca.bgcengineering.promogearreworked.api.products.image.secured.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.Product;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductImage;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SecuredProductImageMapper {

    @Autowired
    private ProductRepository productRepo;

    @Mapping(target = "src", ignore = true)
    @Mapping(target = "blobId", ignore = true)
    public abstract SecuredProductImageCreate toCreate(Long productId, String alt, Integer position, MultipartFile image);

    @Mapping(source = "productImageCreate.productId", target = "product.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productVariants", ignore = true)
    public abstract ProductImage fromCreate(SecuredProductImageCreate productImageCreate);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "src", ignore = true)
    @Mapping(target = "blobId", ignore = true)
    @Mapping(target = "productVariants", ignore = true)
    public abstract ProductImage fromUpdate(SecuredProductImageUpdate productImageUpdate,
                                            @MappingTarget ProductImage image);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "src", ignore = true)
    @Mapping(target = "blobId", ignore = true)
    @Mapping(target = "productVariants", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract ProductImage fromPartialUpdate(SecuredProductImagePartialUpdate productImagePartialUpdate,
                                                   @MappingTarget ProductImage image);

    public abstract SecuredProductImageResponse toResponse(ProductImage image);

    public SecuredProductImageBatchResponse toBatchResponse(List<ProductImage> images) {
        return new SecuredProductImageBatchResponse(images.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    protected Product mapProductFromProductId(Long productId) {
        return productId == null || productId == 0 ? null : productRepo.getById(productId);
    }

}
