package com.example.bgcpromogearreworked.api.products.product.general;

import com.example.bgcpromogearreworked.api.products.product.ProductService;
import com.example.bgcpromogearreworked.api.products.product.general.dto.GeneralProductMapper;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
class GeneralProductService extends ProductService {

    private final GeneralProductMapper mapper;

    public GeneralProductService(ProductRepository repo, GeneralProductMapper mapper) {
        super(repo);
        this.mapper = mapper;
    }

    Product handleProductGet(Long productId) {
        return getProduct(productId);
    }

    Streamable<Product> handleProductBatchGet() {
        return getProducts();
    }

}
