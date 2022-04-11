package ca.bgcengineering.promogearreworked.api.products.image.general;

import ca.bgcengineering.promogearreworked.persistence.entities.ProductImage;
import ca.bgcengineering.promogearreworked.api.products.image.ProductImageService;
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
