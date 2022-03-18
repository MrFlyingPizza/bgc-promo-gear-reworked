package com.example.bgcpromogearreworked.api.products.product.secured;

import com.example.bgcpromogearreworked.api.products.product.ProductService;
import com.example.bgcpromogearreworked.api.products.product.secured.dto.SecuredProductCreate;
import com.example.bgcpromogearreworked.api.products.product.secured.dto.SecuredProductMapper;
import com.example.bgcpromogearreworked.api.products.product.secured.dto.SecuredProductPartialUpdate;
import com.example.bgcpromogearreworked.api.products.product.secured.dto.SecuredProductUpdate;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
class SecuredProductService extends ProductService {

    private final SecuredProductMapper mapper;

    public SecuredProductService(ProductRepository repo, SecuredProductMapper mapper) {
        super(repo);
        this.mapper = mapper;
    }

    Product handleProductGet(Long productId) {
        return getProduct(productId);
    }

    Streamable<Product> handleProductBatchGet() {
        return getProducts();
    }

    Product handleProductCreate(@Valid SecuredProductCreate productCreate) {
        return createProduct(productCreate, mapper::fromCreate);
    }

    Product handleProductPartialUpdate(@Valid SecuredProductPartialUpdate productPartialUpdate) {
        return updateProduct(productPartialUpdate.getId(), productPartialUpdate, mapper::fromPartialUpdate);
    }

    Product handleProductUpdate(@Valid SecuredProductUpdate productUpdate) {
        return updateProduct(productUpdate.getId(), productUpdate, mapper::fromUpdate);
    }

}
