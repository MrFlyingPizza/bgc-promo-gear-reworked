package com.example.bgcpromogearreworked.api.products.images;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductImageNotFoundException;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductImageSaveFailedException;
import com.example.bgcpromogearreworked.api.products.images.dto.secured.SecuredProductImageCreate;
import com.example.bgcpromogearreworked.api.products.images.dto.secured.SecuredProductImageMapper;
import com.example.bgcpromogearreworked.api.products.images.dto.secured.SecuredProductImagePartialUpdate;
import com.example.bgcpromogearreworked.api.products.images.dto.secured.SecuredProductImageUpdate;
import com.example.bgcpromogearreworked.persistence.entities.ProductImage;
import com.example.bgcpromogearreworked.persistence.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Validated
public class ProductImageService {

    private final ProductImageBlobService blobStorageService;
    private final ProductImageRepository imageRepo;
    private final SecuredProductImageMapper mapper;

    public boolean checkProductImageExists(Long productId, Long imageId) {
        return imageRepo.existsByProductIdAndId(productId, imageId);
    }

    ProductImage handleProductImageCreate(@Valid SecuredProductImageCreate imageCreate) {
        ProductImageBlobService.ImageBlobResult result = blobStorageService.saveProductImage(imageCreate.getProductId(),
                imageCreate.getImage());
        if (result == null) { // remove entity if save image blob fails
            throw new ProductImageSaveFailedException();
        }
        ProductImage productImage = mapper.fromCreate(imageCreate);
        productImage.setSrc(result.getUrl());
        productImage.setBlobId(result.getBlobId());
        return imageRepo.saveAndFlush(productImage);
    }

    ProductImage handleProductImageGet(Long imageId) {
        return imageRepo.findById(imageId).orElseThrow(ProductImageNotFoundException::new);
    }

    Iterable<ProductImage> handleProductImageBatchGet(Long productId) {
        return imageRepo.findAllByProductId(productId);
    }

    ProductImage handleProductImageUpdate(@Valid SecuredProductImageUpdate imageUpdate) {
        ProductImage image = imageRepo.findById(imageUpdate.getId()).orElseThrow(ProductImageNotFoundException::new);
        return imageRepo.saveAndFlush(mapper.fromUpdate(imageUpdate, image));
    }

    ProductImage handleProductImagePartialUpdate(@Valid SecuredProductImagePartialUpdate imagePartialUpdate) {
        ProductImage image = imageRepo.findById(imagePartialUpdate.getProductId())
                .orElseThrow(ProductImageNotFoundException::new);
        return imageRepo.saveAndFlush(mapper.fromPartialUpdate(imagePartialUpdate, image));
    }

    void handleProductImageDelete(Long productId, Long imageId) {
        ProductImage image = imageRepo.findById(imageId).orElseThrow(ProductImageNotFoundException::new);
        blobStorageService.deleteProductImage(productId, image.getBlobId());
        imageRepo.delete(image);
    }

}
