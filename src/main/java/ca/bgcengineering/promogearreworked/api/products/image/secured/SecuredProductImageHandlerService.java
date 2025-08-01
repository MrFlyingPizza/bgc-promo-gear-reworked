package ca.bgcengineering.promogearreworked.api.products.image.secured;

import ca.bgcengineering.promogearreworked.api.products.exceptions.ProductImageSaveFailedException;
import ca.bgcengineering.promogearreworked.api.products.image.ProductImageBlobService;
import ca.bgcengineering.promogearreworked.api.products.image.ProductImageService;
import ca.bgcengineering.promogearreworked.api.products.image.secured.dto.SecuredProductImageCreate;
import ca.bgcengineering.promogearreworked.api.products.image.secured.dto.SecuredProductImageMapper;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductImage;
import ca.bgcengineering.promogearreworked.api.products.image.secured.dto.SecuredProductImagePartialUpdate;
import ca.bgcengineering.promogearreworked.api.products.image.secured.dto.SecuredProductImageUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class SecuredProductImageHandlerService {

    private final ProductImageService imageService;
    private final ProductImageBlobService blobStorageService;
    private final SecuredProductImageMapper mapper;


    ProductImage handleProductImageCreate(@Valid SecuredProductImageCreate imageCreate) {
        ProductImageBlobService.ImageBlobResult result = blobStorageService.saveProductImage(imageCreate.getProductId(),
                imageCreate.getImage());
        if (result == null || result.getBlobId() == null || result.getUrl() == null) {
            throw new ProductImageSaveFailedException();
        }
        imageCreate.setSrc(result.getUrl());
        imageCreate.setBlobId(result.getBlobId());
        return imageService.createProductImage(imageCreate, mapper::fromCreate);
    }

    ProductImage handleProductImageGet(Long imageId) {
        return imageService.getProductImage(imageId);
    }

    List<ProductImage> handleProductImageBatchGet(Long productId) {
        return imageService.getProductImages(productId);
    }

    ProductImage handleProductImageUpdate(@Valid SecuredProductImageUpdate imageUpdate) {
        return imageService.updateProductImage(imageUpdate.getId(), imageUpdate, mapper::fromUpdate);
    }

    ProductImage handleProductImagePartialUpdate(@Valid SecuredProductImagePartialUpdate imagePartialUpdate) {
        return imageService.updateProductImage(imagePartialUpdate.getId(), imagePartialUpdate, mapper::fromPartialUpdate);
    }

    void handleProductImageDelete(Long imageId) {
        imageService.deleteProductImage(imageId);
    }

}
