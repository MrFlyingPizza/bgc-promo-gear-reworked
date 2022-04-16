package ca.bgcengineering.promogearreworked.api.products.product.secured;

import ca.bgcengineering.promogearreworked.api.products.exceptions.ProductNotFoundException;
import ca.bgcengineering.promogearreworked.api.products.product.ProductService;
import ca.bgcengineering.promogearreworked.api.products.product.secured.dto.*;
import ca.bgcengineering.promogearreworked.persistence.entities.Product;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/secured/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredProductController {

    private final ProductService productService;
    private final SecuredProductHandlerService handlerService;
    private final SecuredProductMapper mapper;

    @PostMapping
    private SecuredProductResponse createProduct(@RequestBody SecuredProductCreate productCreate) {
        Product result = handlerService.handleProductCreate(productCreate);
        return mapper.toResponse(result);
    }

    @GetMapping("/{productId}")
    private SecuredProductResponse getProduct(@PathVariable Long productId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        Product result = handlerService.handleProductGet(productId);
        return mapper.toResponse(result);
    }

    @GetMapping
    private SecuredProductBatchResponse getProductBatch(@QuerydslPredicate(root = Product.class) Predicate predicate,
                                                        Pageable pageable,
                                                        @RequestParam(defaultValue = "false") Boolean paged) {
        if (!paged) {
            pageable = Pageable.unpaged();
        }
        return mapper.toBatchResponse(handlerService.handleProductBatchGet(predicate, pageable));
    }

    @PatchMapping("/{productId}")
    private SecuredProductResponse updateProductPartial(@PathVariable Long productId,
                                                        @RequestBody SecuredProductPartialUpdate productPartialUpdate) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        productPartialUpdate.setId(productId);
        return mapper.toResponse(handlerService.handleProductPartialUpdate(productPartialUpdate));
    }

    @PutMapping("/{productId}")
    private SecuredProductResponse updateProduct(@PathVariable Long productId,
                                                 @RequestBody SecuredProductUpdate productUpdate) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        productUpdate.setId(productId);
        Product result = handlerService.handleProductUpdate(productUpdate);
        return mapper.toResponse(result);
    }



}
