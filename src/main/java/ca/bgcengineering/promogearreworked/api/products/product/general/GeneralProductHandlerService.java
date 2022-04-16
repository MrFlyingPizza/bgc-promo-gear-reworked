package ca.bgcengineering.promogearreworked.api.products.product.general;

import ca.bgcengineering.promogearreworked.api.products.product.ProductService;
import ca.bgcengineering.promogearreworked.persistence.entities.Product;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class GeneralProductHandlerService {

    private final ProductService productService;

    Product handleProductGet(Long productId) {
        return productService.getProduct(productId);
    }

    Page<Product> handleProductBatchGet(Predicate predicate, Pageable pageable) {
        return productService.getProducts(predicate, pageable);
    }

}
