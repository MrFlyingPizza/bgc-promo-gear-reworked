package ca.bgcengineering.promogearreworked.api.products.image.general;

import ca.bgcengineering.promogearreworked.api.products.exceptions.ProductImageNotFoundException;
import ca.bgcengineering.promogearreworked.api.products.exceptions.ProductNotFoundException;
import ca.bgcengineering.promogearreworked.api.products.product.ProductService;
import ca.bgcengineering.promogearreworked.api.products.image.ProductImageService;
import ca.bgcengineering.promogearreworked.api.products.image.general.dto.GeneralProductImageBatchResponse;
import ca.bgcengineering.promogearreworked.api.products.image.general.dto.GeneralProductImageMapper;
import ca.bgcengineering.promogearreworked.api.products.image.general.dto.GeneralProductImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/{productId}/images")
class GeneralProductImageController {

    private final GeneralProductImageHandlerService handlerService;
    private final ProductService productService;
    private final ProductImageService imageService;
    private final GeneralProductImageMapper mapper;

    @GetMapping("/{imageId}")
    private GeneralProductImageResponse getProductImage(@PathVariable Long productId, @PathVariable Long imageId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (!imageService.checkProductImageExistsOnProduct(productId, imageId)) {
            throw new ProductImageNotFoundException();
        }
        return mapper.toResponse(handlerService.handleProductImageGet(imageId));
    }

    @GetMapping
    private GeneralProductImageBatchResponse getProductImageBatch(@PathVariable Long productId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        return mapper.toBatchResponse(handlerService.handleProductImageBatchGet(productId));
    }

}
