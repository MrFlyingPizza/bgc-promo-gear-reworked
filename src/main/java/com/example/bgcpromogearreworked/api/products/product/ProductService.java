package com.example.bgcpromogearreworked.api.products.product;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.Option;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import com.example.bgcpromogearreworked.persistence.repositories.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;
    private final ProductVariantRepository variantRepo;

    public boolean checkProductExists(Long productId) {
        assert productId != null;
        return repo.existsById(productId);
    }

    public Product getProduct(Long productId) throws ProductNotFoundException {
        assert productId != null;
        return repo.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> getProducts() {
        return repo.findAll();
    }

    public <T> Product createProduct(T source, Function<T, Product> mapper) {
        assert source != null && mapper != null;
        Product product = mapper.apply(source);
        assert product.getId() == null;
        return repo.saveAndFlush(product);
    }

    public <T> Product updateProduct(Long productId, T source, BiFunction<T, Product, Product> mapper) {
        assert productId != null && source != null && mapper != null;
        Product original = repo.findById(productId).orElseThrow(ProductNotFoundException::new);

        Set<Option> originalOptions = new HashSet<>(original.getOptions());

        Product updated = mapper.apply(source, original);
        assert updated.getId().equals(productId);

        if (!originalOptions.equals(updated.getOptions())) {
            invalidateProductVariants(updated);
        }
        return repo.saveAndFlush(mapper.apply(source, updated));
    }

    public void deleteProduct(Long productId) {
        assert productId != null;
        repo.deleteById(productId);
    }

    private void invalidateProductVariants(Product product) {
        assert product != null;
        product.getVariants().forEach(variant -> variant.setIsInUse(false));
        variantRepo.saveAllAndFlush(product.getVariants());
    }

}
