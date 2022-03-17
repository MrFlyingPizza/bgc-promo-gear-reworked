package com.example.bgcpromogearreworked.api.products.images;

import com.azure.storage.blob.BlobAsyncClient;
import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobServiceAsyncClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductImageBlobService {

    private final BlobServiceAsyncClient asyncClient;

    private static String buildContainerName(Long productId) {
        if (productId == null) throw new NullPointerException("Product ID cannot be null when building blob container ID.");
        return productId.toString();
    }

    public ProductImageBlobService(BlobServiceClientBuilder blobServiceClientBuilder) {
        this.asyncClient = blobServiceClientBuilder.buildAsyncClient();
    }

    private BlobContainerAsyncClient getBlobContainerClientByProductId(Long productId) {
        return asyncClient.getBlobContainerAsyncClient(buildContainerName(productId));
    }

    // TODO: 2022-03-16 implement blob storage service
    String saveProductImage(Long productId, MultipartFile imageFile) {
        imageFile.getOriginalFilename();
        return null;
    }

}
