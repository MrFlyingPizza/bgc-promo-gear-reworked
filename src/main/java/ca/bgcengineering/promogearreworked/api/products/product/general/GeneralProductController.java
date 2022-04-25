package ca.bgcengineering.promogearreworked.api.products.product.general;

import ca.bgcengineering.promogearreworked.api.products.exceptions.ProductNotFoundException;
import ca.bgcengineering.promogearreworked.api.products.product.ProductService;
import ca.bgcengineering.promogearreworked.api.products.product.general.dto.GeneralProductBatchResponse;
import ca.bgcengineering.promogearreworked.api.products.product.general.dto.GeneralProductMapper;
import ca.bgcengineering.promogearreworked.api.products.product.general.dto.GeneralProductResponse;
import ca.bgcengineering.promogearreworked.persistence.entities.Product;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralProductController {

    private final ProductService productService;
    private final GeneralProductHandlerService handlerService;
    private final GeneralProductMapper mapper;

    @GetMapping("/{productId}")
    private GeneralProductResponse getProduct(@PathVariable Long productId) {
        if (!productService.checkProductExistsAndIsPublished(productId)) {
            throw new ProductNotFoundException();
        }
        Product result = handlerService.handleProductGet(productId);
        return mapper.toResponse(result);
    }

    @GetMapping
    private GeneralProductBatchResponse getProductBatch(@QuerydslPredicate(root = Product.class) Predicate predicate,
                                                        Pageable pageable,
                                                        @RequestParam(defaultValue = "false") Boolean paged) {
        if (!paged) {
            pageable = Pageable.unpaged();
        }
        return mapper.toBatchResponse(handlerService.handleProductBatchGet(predicate, pageable));
    }

}
