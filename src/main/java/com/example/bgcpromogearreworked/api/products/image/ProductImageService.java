package com.example.bgcpromogearreworked.api.products.image;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductImageNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.ProductImage;
import com.example.bgcpromogearreworked.persistence.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository imageRepo;

    public boolean checkProductImageExistsOnProduct(Long productId, Long imageId) {
        assert productId != null && imageId != null;
        return imageRepo.existsByProductIdAndId(productId, imageId);
    }

    public ProductImage getProductImage(Long imageId) {
        assert imageId != null;
        return imageRepo.findById(imageId).orElseThrow(ProductImageNotFoundException::new);
    }

    public List<ProductImage> getProductImages(Long productId) {
        assert productId != null;
        return imageRepo.findAllByProductId(productId);
    }

    public <T> ProductImage createProductImage(T source, Function<T, ProductImage> mapper) {
        assert source != null && mapper != null;
        ProductImage image = mapper.apply(source);
        assert image.getId() == null;
        return imageRepo.saveAndFlush(image);
    }

    public <T> ProductImage updateProductImage(Long imageId, T source, BiFunction<T, ProductImage, ProductImage> mapper) {
        assert imageId != null && source != null && mapper != null;
        ProductImage image = mapper.apply(source, imageRepo.findById(imageId).orElseThrow(ProductImageNotFoundException::new));
        assert image.getId().equals(imageId);
        return imageRepo.saveAndFlush(image);
    }

    public void deleteProductImage(Long imageId) {
        assert imageId != null;
        imageRepo.deleteById(imageId);
    }

}
