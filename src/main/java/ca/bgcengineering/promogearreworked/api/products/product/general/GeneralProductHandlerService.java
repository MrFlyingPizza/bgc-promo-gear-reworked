package ca.bgcengineering.promogearreworked.api.products.product.general;

import ca.bgcengineering.promogearreworked.api.products.product.ProductService;
import ca.bgcengineering.promogearreworked.persistence.entities.Product;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import ca.bgcengineering.promogearreworked.persistence.entities.QProduct;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class GeneralProductHandlerService {

    private final ProductService productService;

    private Set<ProductVariant> filterVariants(Set<ProductVariant> variants) {
        return variants.stream().filter(ProductVariant::getIsInUse).collect(Collectors.toSet());
    }

    Product handleProductGet(Long productId) {
        Product product = productService.getProduct(productId);
        product.setVariants(filterVariants(product.getVariants()));
        return product;
    }

    Page<Product> handleProductBatchGet(Predicate predicate, Pageable pageable) {
        QProduct product = QProduct.product;
        predicate = product.isPublished.eq(true).and(predicate);
        Page<Product> products = productService.getProducts(predicate, pageable);
        products.forEach(item -> item.setVariants(filterVariants(item.getVariants())));
        return products;
    }

}
