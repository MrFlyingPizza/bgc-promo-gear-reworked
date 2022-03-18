package com.example.bgcpromogearreworked.api.products.product;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.api.products.variant.ProductVariantService;
import com.example.bgcpromogearreworked.persistence.entities.Option;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public abstract class ProductService {

    private final ProductRepository repo;
    private final ProductVariantService variantService;

    public boolean checkProductExists(Long productId) {
        return repo.existsById(productId);
    }

    public Product getProduct(Long productId) throws ProductNotFoundException {
        return repo.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public Streamable<Product> getProducts() {
        return Streamable.of(repo.findAll());
    }

    public <T> Product createProduct(T source, Function<T, Product> mapper) {
        Product product = mapper.apply(source);
        if (product.getId() != null) {
            throw new RuntimeException("Product ID must be null.");
        }
        return repo.saveAndFlush(product);
    }

    public <T> Product updateProduct(Long productId, T source, BiFunction<T, Product, Product> mapper) throws ProductNotFoundException {
        Product original = repo.findById(productId).orElseThrow(ProductNotFoundException::new);
        Set<Option> originalOptions = original.getOptions();
        Product product = mapper.apply(source, original);
        if (!product.getId().equals(productId)) {
            throw new RuntimeException("Mapper function is forbidden to modify product ID.");
        }
        if (checkOptionsUpdated(originalOptions, product.getOptions())) {
            invalidateProductVariants(product);
        }
        return repo.saveAndFlush(mapper.apply(source, product));
    }

    public void deleteProduct(Long productId) {
        repo.deleteById(productId);
    }

    public void deleteProduct(Product product) {
        repo.delete(product);
    }

    private boolean checkOptionsUpdated(Set<Option> current, Set<Option> updated) {
        return current.equals(updated);
    }

    private void invalidateProductVariants(Product product) {
        product.getVariants().forEach(variant -> {
            variant.setIsValid(false);
            variantService.updateProductVariant(variant);
        });
    }

}
