package com.example.bgcpromogearreworked.api.products.image.general;

import com.example.bgcpromogearreworked.api.products.image.ProductImageService;
import com.example.bgcpromogearreworked.persistence.entities.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class GeneralProductImageHandlerService {

    private final ProductImageService imageService;

    ProductImage handleProductImageGet(Long imageId) {
        return imageService.getProductImage(imageId);
    }

    List<ProductImage> handleProductImageBatchGet(Long productId) {
        return imageService.getProductImages(productId);
    }

}
