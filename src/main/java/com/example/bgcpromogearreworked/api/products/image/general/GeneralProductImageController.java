package com.example.bgcpromogearreworked.api.products.image.general;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductImageNotFoundException;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.image.ProductImageService;
import com.example.bgcpromogearreworked.api.products.image.general.dto.GeneralProductImageBatchResponse;
import com.example.bgcpromogearreworked.api.products.image.general.dto.GeneralProductImageMapper;
import com.example.bgcpromogearreworked.api.products.image.general.dto.GeneralProductImageResponse;
import com.example.bgcpromogearreworked.api.products.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/{productId}/images")
public class GeneralProductImageController {

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
