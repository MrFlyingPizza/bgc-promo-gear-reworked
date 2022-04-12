package ca.bgcengineering.promogearreworked.api.products.product.secured;

import ca.bgcengineering.promogearreworked.api.products.product.ProductService;
import ca.bgcengineering.promogearreworked.api.products.product.secured.dto.SecuredProductCreate;
import ca.bgcengineering.promogearreworked.api.products.product.secured.dto.SecuredProductMapper;
import ca.bgcengineering.promogearreworked.api.products.product.secured.dto.SecuredProductPartialUpdate;
import ca.bgcengineering.promogearreworked.api.products.product.secured.dto.SecuredProductUpdate;
import ca.bgcengineering.promogearreworked.persistence.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class SecuredProductHandlerService {

    private final SecuredProductMapper mapper;
    private final ProductService productService;

    Product handleProductGet(Long productId) {
        return productService.getProduct(productId);
    }

    List<Product> handleProductBatchGet() {
        return productService.getProducts();
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
