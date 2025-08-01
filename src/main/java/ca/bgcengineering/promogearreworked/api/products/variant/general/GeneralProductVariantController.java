package ca.bgcengineering.promogearreworked.api.products.variant.general;

import ca.bgcengineering.promogearreworked.api.products.exceptions.ProductNotFoundException;
import ca.bgcengineering.promogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import ca.bgcengineering.promogearreworked.api.products.product.ProductService;
import ca.bgcengineering.promogearreworked.api.products.variant.ProductVariantService;
import ca.bgcengineering.promogearreworked.api.products.variant.general.dto.GeneralProductVariantBatchResponse;
import ca.bgcengineering.promogearreworked.api.products.variant.general.dto.GeneralProductVariantMapper;
import ca.bgcengineering.promogearreworked.api.products.variant.general.dto.GeneralProductVariantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/products/{productId}/variants", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralProductVariantController {

    private final ProductService productService;
    private final ProductVariantService variantService;
    private final GeneralProductVariantHandlerService handlerService;
    private final GeneralProductVariantMapper mapper;

    @GetMapping("/{variantId}")
    private GeneralProductVariantResponse getProductVariant(@PathVariable Long productId, @PathVariable Long variantId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (!variantService.checkProductVariantExistsOnProduct(productId, variantId)) {
            throw new ProductVariantNotFoundException();
        }
        return mapper.toResponse(handlerService.handleProductVariantGet(variantId));
    }

    @GetMapping
    private GeneralProductVariantBatchResponse getProductVariantBatch(@PathVariable Long productId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        return mapper.toBatchResponse(handlerService.handleProductVariantBatchGet(productId));
    }

}
