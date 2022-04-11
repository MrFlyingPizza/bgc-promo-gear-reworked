package ca.bgcengineering.promogearreworked.api.products.product.general;

import ca.bgcengineering.promogearreworked.api.products.product.ProductService;
import ca.bgcengineering.promogearreworked.api.products.product.general.dto.GeneralProductMapper;
import ca.bgcengineering.promogearreworked.persistence.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class GeneralProductHandlerService {

    private final ProductService productService;
    private final GeneralProductMapper mapper;

    Product handleProductGet(Long productId) {
        return productService.getProduct(productId);
    }

    List<Product> handleProductBatchGet() {
        return productService.getProducts();
    }

}
