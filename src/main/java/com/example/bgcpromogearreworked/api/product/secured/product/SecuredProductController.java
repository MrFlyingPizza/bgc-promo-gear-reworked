package com.example.bgcpromogearreworked.api.product.secured.product;

import com.example.bgcpromogearreworked.api.product.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.product.secured.product.dto.ProductCreateDto;
import com.example.bgcpromogearreworked.api.product.secured.product.dto.ProductResponseDto;
import com.example.bgcpromogearreworked.api.product.secured.product.dto.ProductUpdateDto;
import com.example.bgcpromogearreworked.api.product.secured.product.dto.ProductsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/secured/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredProductController {

    private final SecuredProductService securedProductService;

    @PostMapping
    private ProductResponseDto createProduct(@RequestBody @Valid ProductCreateDto productCreateDto) {
        return securedProductService.handleProductCreate(productCreateDto);
    }

    @GetMapping("/{productId}")
    private ProductResponseDto getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        if (!securedProductService.doesProductExist(productId)) {
            throw new ProductNotFoundException();
        }
        return securedProductService.handleProductGet(productId);
    }

    @GetMapping("")
    private ProductsResponseDto getMultipleProducts() {
        return securedProductService.handleProductGetMultiple();
    }

    @PutMapping("/{productId}")
    private ProductResponseDto updateProduct(@PathVariable Long productId,
                                             @RequestBody @Valid ProductUpdateDto productUpdateDto) throws ProductNotFoundException {
        if (!securedProductService.doesProductExist(productId)) {
            throw new ProductNotFoundException();
        }
        return securedProductService.handleProductUpdate(productId, productUpdateDto);
    }

}
