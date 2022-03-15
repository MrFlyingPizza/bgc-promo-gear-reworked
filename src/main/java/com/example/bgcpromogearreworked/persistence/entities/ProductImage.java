package com.example.bgcpromogearreworked.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "product_image")
@Entity
@Getter
@Setter
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
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
}