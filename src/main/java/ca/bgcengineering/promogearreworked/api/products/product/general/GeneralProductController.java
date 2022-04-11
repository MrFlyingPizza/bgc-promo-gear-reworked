package ca.bgcengineering.promogearreworked.api.products.product.general;

import ca.bgcengineering.promogearreworked.api.products.exceptions.ProductNotFoundException;
import ca.bgcengineering.promogearreworked.api.products.product.ProductService;
import ca.bgcengineering.promogearreworked.api.products.product.general.dto.GeneralProductBatchResponse;
import ca.bgcengineering.promogearreworked.api.products.product.general.dto.GeneralProductMapper;
import ca.bgcengineering.promogearreworked.api.products.product.general.dto.GeneralProductResponse;
import ca.bgcengineering.promogearreworked.persistence.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralProductController {

    private final ProductService productService;
    private final GeneralProductHandlerService handlerService;
    private final GeneralProductMapper mapper;

    @GetMapping("/{productId}")
    private GeneralProductResponse getProduct(@PathVariable Long productId) {
        if (!productService.checkProductExists(productId)) {
            throw new ProductNotFoundException();
        }
        Product result = handlerService.handleProductGet(productId);
        return mapper.toResponse(result);
    }

    @GetMapping
    private GeneralProductBatchResponse getProductBatch() {
        List<Product> result = handlerService.handleProductBatchGet();
        return mapper.toBatchResponse(result);
    }

}
