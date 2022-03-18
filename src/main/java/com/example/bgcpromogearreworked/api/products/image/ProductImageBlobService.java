package com.example.bgcpromogearreworked.api.products.image;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Service
public class ProductImageBlobService {

    private static final String PRODUCT_IMAGE_CONTAINER_NAME = "product-images";

    /**
     * Result after saving an image to blob storage.
     */
    @Getter
    @RequiredArgsConstructor
    public static class ImageBlobResult {
        private final String url;
        private final UUID blobId;
    }

    private final BlobContainerClient blobContainerClient;

    public ProductImageBlobService(BlobServiceClientBuilder blobServiceClientBuilder) {
        this.blobContainerClient = getProductImageContainerClient(blobServiceClientBuilder.buildClient());
    }

    /**
     * Saves an image as a blob to the blob container that has the given productId as the name along with product ID and random UUID as the tag
     * @param productId The ID of the product which the blob container is named by.
     * @param imageFile The image file to be saved as a blob to the blob container.
     * @return Returns {@link ImageBlobResult} if successful, otherwise return null.
     */
    public ImageBlobResult saveProductImage(Long productId, MultipartFile imageFile) {
        if (productId == null || imageFile == null) {
            throw new NullPointerException();
        }
        if (imageFile.isEmpty()) {
            throw new RuntimeException("Image file cannot be empty.");
        }
        try {
            UUID imageBlobId = UUID.randomUUID();
            BlobClient blobClient = blobContainerClient.getBlobClient(buildBlobName(imageBlobId));
            blobClient.upload(BinaryData.fromBytes(imageFile.getBytes()));
            blobClient.setHttpHeaders(buildHeaders(imageFile.getContentType()));
            blobClient.setTags(buildTags(productId, imageBlobId));
            return new ImageBlobResult(blobClient.getBlobUrl(), imageBlobId);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteProductImage(Long productId, UUID imageBlobId) {
        if (productId == null || imageBlobId == null) {
            throw new NullPointerException();
        }
        blobContainerClient.getBlobClient(buildBlobName(imageBlobId)).delete();
    }

    private HashMap<String, String> buildTags(Long productId, UUID blobId) {
        final String PRODUCT_ID_TAG_KEY = "product_id";
        final String BLOB_ID_TAG_KEY = "blob_id";
        HashMap<String, String> tags = new HashMap<>();
        tags.put(PRODUCT_ID_TAG_KEY, productId.toString());
        tags.put(BLOB_ID_TAG_KEY, blobId.toString());
        return tags;
    }

    private static BlobHttpHeaders buildHeaders(String contentType) {
        return new BlobHttpHeaders().setContentType(contentType);
    }

    private static String buildBlobName(UUID blobId) {
        return blobId.toString().replace("-","");
    }

    private static BlobContainerClient getProductImageContainerClient(BlobServiceClient blobServiceClient) {
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(PRODUCT_IMAGE_CONTAINER_NAME);
        if (!blobContainerClient.exists()) {
            blobContainerClient = blobServiceClient.createBlobContainer(PRODUCT_IMAGE_CONTAINER_NAME);
        }
        return blobContainerClient;
    }


}
