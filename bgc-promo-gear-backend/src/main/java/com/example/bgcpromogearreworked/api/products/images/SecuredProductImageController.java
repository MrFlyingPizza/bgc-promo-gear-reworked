package com.example.bgcpromogearreworked.api.products.images;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.images.dto.secured.*;
import com.example.bgcpromogearreworked.api.products.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secured/products/{productId}/images")
public class SecuredProductImageController {

    private final ProductImageService imageService;
    private final ProductService productService;
    private final SecuredProductImageMapper mapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private SecuredProductImageResponse createProductImage(@PathVariable Long productId,
                                                           @RequestParam String alt,
                                                           @RequestParam Integer position,
                                                           @RequestPart MultipartFile image) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        SecuredProductImageCreate imageCreate = mapper.toCreate(productId, alt, position, image);
        return mapper.toResponse(imageService.handleProductImageCreate(imageCreate));
    }

    @GetMapping("/{imageId}")
    private SecuredProductImageResponse getProductImage(@PathVariable Long productId, @PathVariable Long imageId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        return mapper.toResponse(imageService.handleProductImageGet(imageId));
    }

    @GetMapping
    private SecuredProductImageBatchResponse getProductImageBatch(@PathVariable Long productId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        return mapper.toBatchResponse(imageService.handleProductImageBatchGet(productId));
    }

    @PutMapping("/{imageId}")
    private SecuredProductImageResponse updateProductImage(@PathVariable Long productId,
                                                           @PathVariable Long imageId,
                                                           @RequestBody SecuredProductImageUpdate imageUpdate) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (!imageService.checkProductImageExists(productId, imageId)) {
            throw new ProductNotFoundException();
        }
        imageUpdate.setProductId(productId);
        imageUpdate.setId(imageId);
        return mapper.toResponse(imageService.handleProductImageUpdate(imageUpdate));
    }

    @PatchMapping("/{imageId}")
    private SecuredProductImageResponse updateProductImagePartial(@PathVariable Long productId,
                                                                  @PathVariable Long imageId,
                                                                  @RequestBody SecuredProductImagePartialUpdate imagePartialUpdate) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (!imageService.checkProductImageExists(productId, imageId)) {
            throw new ProductNotFoundException();
        }
        imagePartialUpdate.setProductId(productId);
        imagePartialUpdate.setId(imageId);
        return mapper.toResponse(imageService.handleProductImagePartialUpdate(imagePartialUpdate));
    }

    @DeleteMapping("/{imageId}")
    private void deleteProductImage(@PathVariable Long productId, @PathVariable Long imageId) {
        imageService.handleProductImageDelete(productId, imageId);
    }

}
