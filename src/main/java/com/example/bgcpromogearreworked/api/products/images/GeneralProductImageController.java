package com.example.bgcpromogearreworked.api.products.images;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductImageNotFoundException;
import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.images.dto.general.GeneralProductImageBatchResponse;
import com.example.bgcpromogearreworked.api.products.images.dto.general.GeneralProductImageMapper;
import com.example.bgcpromogearreworked.api.products.images.dto.general.GeneralProductImageResponse;
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

    private final ProductImageService imageService;
    private final ProductService productService;
    private final GeneralProductImageMapper mapper;

    @GetMapping("/{imageId}")
    private GeneralProductImageResponse getProductImage(@PathVariable Long productId, @PathVariable Long imageId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (!imageService.checkProductImageExists(productId, imageId)) {
            throw new ProductImageNotFoundException();
        }
        return mapper.toResponse(imageService.handleProductImageGet(imageId));
    }

    @GetMapping
    private GeneralProductImageBatchResponse getProductImageBatch(@PathVariable Long productId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        return mapper.toBatchResponse(imageService.handleProductImageBatchGet(productId));
    }

}
