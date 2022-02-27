package com.example.bgcpromogearreworked.api.product;

import com.example.bgcpromogearreworked.api.category.Category;
import com.example.bgcpromogearreworked.api.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Table(name = "product")
@Entity
@Getter
@Setter
public class Product {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "brand", length = 60)
    private String brand;

    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false, insertable = false, updatable = false)
    private Category category;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "is_published", nullable = false)
    private Boolean isPublished = false;

    @Column(name = "is_big_item", nullable = false)
    private Boolean isBigItem = false;

    @Column(name = "is_wait_list_enabled", nullable = false)
    private Boolean isWaitListEnabled = false;

    @OneToMany(mappedBy = "product")
    private Set<Option> options;

    @OneToMany(mappedBy = "product")
    private Set<OptionValue> optionValues;

    @OneToMany(mappedBy = "product")
    private Set<ProductVariant> variants;

    @OneToMany(mappedBy = "product")
    private Set<ProductImage> images;

    @CreatedDate
    @Column(name = "created_date")
    private Instant createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @LastModifiedBy
    @JoinColumn(name = "last_modified_by")
    private User lastModifiedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    @JoinColumn(name = "created_by")
    private User createdBy;

}