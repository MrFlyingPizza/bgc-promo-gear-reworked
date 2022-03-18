package com.example.bgcpromogearreworked.api.products.image.general;

import com.example.bgcpromogearreworked.api.products.image.ProductImageService;
import com.example.bgcpromogearreworked.persistence.entities.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralProductImageHandlerService {

    private final ProductImageService imageService;

    ProductImage handleProductImageGet(Long imageId) {
        return imageService.getProductImage(imageId);
    }

    Streamable<ProductImage> handleProductImageBatchGet(Long productId) {
        return imageService.getProductImages(productId);
    }

}
