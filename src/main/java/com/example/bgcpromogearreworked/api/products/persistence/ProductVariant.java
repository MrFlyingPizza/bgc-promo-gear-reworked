package com.example.bgcpromogearreworked.api.products.persistence;

import com.example.bgcpromogearreworked.api.users.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Table(name = "product_variant")
@Entity
@Getter
@Setter
public class ProductVariant {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "wait_list_threshold")
    private Integer waitListThreshold;

    @CreatedDate
    @Column(name = "created_date")
    private Instant createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "last_modified_by")
    private User lastModifiedBy;

    @ManyToOne
    @JoinColumn(name = "image_id", insertable = false, updatable = false)
    private ProductImage image;

    @ManyToMany
    @JoinTable(
            name = "variant_has_option",
            joinColumns = @JoinColumn(name = "variant_id"),
            inverseJoinColumns = {
                @JoinColumn(name = "product_id"),
                @JoinColumn(name = "option_name"),
                @JoinColumn(name = "option_value")
            }
    )
    private Set<OptionValue> optionValues;

}