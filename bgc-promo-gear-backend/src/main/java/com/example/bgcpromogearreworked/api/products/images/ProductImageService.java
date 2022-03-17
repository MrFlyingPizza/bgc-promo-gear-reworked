package com.example.bgcpromogearreworked.api.products.images;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductImageNotFoundException;
import com.example.bgcpromogearreworked.api.products.images.dto.secured.SecuredProductImageCreate;
import com.example.bgcpromogearreworked.api.products.images.dto.secured.SecuredProductImageMapper;
import com.example.bgcpromogearreworked.api.products.images.dto.secured.SecuredProductImagePartialUpdate;
import com.example.bgcpromogearreworked.api.products.images.dto.secured.SecuredProductImageUpdate;
import com.example.bgcpromogearreworked.persistence.entities.ProductImage;
import com.example.bgcpromogearreworked.persistence.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository imageRepo;
    private final SecuredProductImageMapper mapper;

    public boolean checkProductImageExists(Long productId, Long imageId) {
        return imageRepo.existsByProductIdAndId(productId, imageId);
    }

    ProductImage handleProductImageCreate(SecuredProductImageCreate imageCreate) {
        // TODO: 2022-03-16 integrate blob storage here
        //return imageRepo.save(mapper.fromCreate(imageCreate, ));
        return null;
    }

    ProductImage handleProductImageGet(Long imageId) {
        return imageRepo.findById(imageId).orElseThrow(ProductImageNotFoundException::new);
    }

    Iterable<ProductImage> handleProductImageBatchGet(Long productId) {
        return imageRepo.findAllByProductId(productId);
    }

    ProductImage handleProductImageUpdate(SecuredProductImageUpdate imageUpdate) {
        ProductImage image = imageRepo.findById(imageUpdate.getId()).orElseThrow(ProductImageNotFoundException::new);
        return imageRepo.save(mapper.fromUpdate(imageUpdate, image));
    }

    ProductImage handleProductImagePartialUpdate(SecuredProductImagePartialUpdate imagePartialUpdate) {
        ProductImage image = imageRepo.findById(imagePartialUpdate.getProductId())
                .orElseThrow(ProductImageNotFoundException::new);
        return imageRepo.save(mapper.fromPartialUpdate(imagePartialUpdate, image));
    }

}
