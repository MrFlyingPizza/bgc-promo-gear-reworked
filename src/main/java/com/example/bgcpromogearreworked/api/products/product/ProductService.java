package com.example.bgcpromogearreworked.api.products.product;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.Product;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;

    public boolean checkProductExists(Long productId) {
        return repo.existsById(productId);
    }

    public Product getProduct(Long productId) throws ProductNotFoundException {
        return repo.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public Streamable<Product> getProducts() {
        return Streamable.of(repo.findAll());
    }

    public Product createProduct(Product product) {
        return repo.saveAndFlush(product);
    }

    public <T> Product createProduct(T source, Function<T, Product> mapper) {
        return repo.saveAndFlush(mapper.apply(source));
    }

    public <T> Product updateProduct(Long productId, T source, BiFunction<T, Product, Product> mapper) throws ProductNotFoundException {
        Product product = repo.findById(productId).orElseThrow(ProductNotFoundException::new);
        return repo.saveAndFlush(mapper.apply(source, product));
    }

    public void deleteProduct(Long productId) {
        repo.deleteById(productId);
    }

    public void deleteProduct(Product product) {
        repo.delete(product);
    }

}
