package com.example.bgcpromogearreworked.api.products.image.secured;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.image.ProductImageService;
import com.example.bgcpromogearreworked.api.products.image.secured.dto.*;
import com.example.bgcpromogearreworked.api.products.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secured/products/{productId}/images")
class SecuredProductImageController {

    private final ProductService productService;
    private final ProductImageService imageService;
    private final SecuredProductImageHandlerService handlerService;
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
        return mapper.toResponse(handlerService.handleProductImageCreate(imageCreate));
    }

    @GetMapping("/{imageId}")
    private SecuredProductImageResponse getProductImage(@PathVariable Long productId, @PathVariable Long imageId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        return mapper.toResponse(handlerService.handleProductImageGet(imageId));
    }

    @GetMapping
    private SecuredProductImageBatchResponse getProductImageBatch(@PathVariable Long productId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        return mapper.toBatchResponse(handlerService.handleProductImageBatchGet(productId));
    }

    @PutMapping("/{imageId}")
    private SecuredProductImageResponse updateProductImage(@PathVariable Long productId,
                                                           @PathVariable Long imageId,
                                                           @RequestBody SecuredProductImageUpdate imageUpdate) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (!imageService.checkProductImageExistsOnProduct(productId, imageId)) {
            throw new ProductNotFoundException();
        }
        imageUpdate.setProductId(productId);
        imageUpdate.setId(imageId);
        return mapper.toResponse(handlerService.handleProductImageUpdate(imageUpdate));
    }

    @PatchMapping("/{imageId}")
    private SecuredProductImageResponse updateProductImagePartial(@PathVariable Long productId,
                                                                  @PathVariable Long imageId,
                                                                  @RequestBody SecuredProductImagePartialUpdate imagePartialUpdate) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        if (!imageService.checkProductImageExistsOnProduct(productId, imageId)) {
            throw new ProductNotFoundException();
        }
        imagePartialUpdate.setProductId(productId);
        imagePartialUpdate.setId(imageId);
        return mapper.toResponse(handlerService.handleProductImagePartialUpdate(imagePartialUpdate));
    }

    @DeleteMapping("/{imageId}")
    private void deleteProductImage(@PathVariable Long productId, @PathVariable Long imageId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        handlerService.handleProductImageDelete(imageId);
    }

}
