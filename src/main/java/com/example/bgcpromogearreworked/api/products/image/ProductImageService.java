package com.example.bgcpromogearreworked.api.products.image;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductImageNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.ProductImage;
import com.example.bgcpromogearreworked.persistence.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository imageRepo;

    public boolean checkProductImageExistsOnProduct(Long productId, Long imageId) {
        return imageRepo.existsByProductIdAndId(productId, imageId);
    }

    public ProductImage getProductImage(Long imageId) {
        return imageRepo.findById(imageId).orElseThrow(ProductImageNotFoundException::new);
    }

    public Streamable<ProductImage> getProductImages(Long productId) {
        return imageRepo.findAllByProductId(productId);
    }

    public ProductImage createProductImage(ProductImage image) {
        if (image.getId() != null) {
            throw new RuntimeException("Image ID field must be null.");
        }
        return imageRepo.saveAndFlush(image);
    }

    public <T> ProductImage createProductImage(T source, Function<T, ProductImage> mapper) {
        ProductImage image = mapper.apply(source);
        if (image.getId() != null) {
            throw new RuntimeException("Image ID field must be null.");
        }
        return imageRepo.saveAndFlush(image);
    }

    public <T> ProductImage updateProductImage(Long imageId, T source, BiFunction<T, ProductImage, ProductImage> mapper) {
        ProductImage image = mapper.apply(source, imageRepo.findById(imageId).orElseThrow(ProductImageNotFoundException::new));
        if (!image.getId().equals(imageId)) {
            throw new RuntimeException("Mapper function is forbidden to modify image ID.");
        }
        return imageRepo.saveAndFlush(image);
    }

    public void deleteProductImage(Long imageId) {
        imageRepo.deleteById(imageId);
    }

    public void deleteProductImage(ProductImage image) {
        imageRepo.delete(image);
    }

}
