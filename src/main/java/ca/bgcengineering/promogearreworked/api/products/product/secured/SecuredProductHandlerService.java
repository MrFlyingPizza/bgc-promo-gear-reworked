package ca.bgcengineering.promogearreworked.api.products.product.secured;

import ca.bgcengineering.promogearreworked.api.products.product.ProductService;
import ca.bgcengineering.promogearreworked.api.products.product.secured.dto.SecuredProductCreate;
import ca.bgcengineering.promogearreworked.api.products.product.secured.dto.SecuredProductMapper;
import ca.bgcengineering.promogearreworked.api.products.product.secured.dto.SecuredProductPartialUpdate;
import ca.bgcengineering.promogearreworked.api.products.product.secured.dto.SecuredProductUpdate;
import ca.bgcengineering.promogearreworked.persistence.entities.Product;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class SecuredProductHandlerService {

    private final SecuredProductMapper mapper;
    private final ProductService productService;

    Product handleProductGet(Long productId) {
        return productService.getProduct(productId);
    }

    Page<Product> handleProductBatchGet(Predicate predicate, Pageable pageable) {
        return productService.getProducts(predicate, pageable);
    }

    Product handleProductCreate(@Valid SecuredProductCreate productCreate) {
        return productService.createProduct(productCreate, mapper::fromCreate);
    }

    Product handleProductPartialUpdate(@Valid SecuredProductPartialUpdate productPartialUpdate) {
        return productService.updateProduct(productPartialUpdate.getId(), productPartialUpdate, mapper::fromPartialUpdate);
    }

    Product handleProductUpdate(@Valid SecuredProductUpdate productUpdate) {
        return productService.updateProduct(productUpdate.getId(), productUpdate, mapper::fromUpdate);
    }

}
