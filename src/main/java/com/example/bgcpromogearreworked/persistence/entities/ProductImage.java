package com.example.bgcpromogearreworked.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Table(name = "product_image")
@Entity
@Getter
@Setter
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "src", nullable = false, length = 256)
    private String src;

    @Column(name = "alt", nullable = false, length = 200)
    private String alt;

    @Column(name = "\"position\"", nullable = false)
    private Integer position;

    @OneToMany(mappedBy = "image")
    private Set<ProductVariant> productVariants;

    @Column(name = "blob_id", nullable = false)
    private UUID blobId;

}