package com.example.bgcpromogearreworked.api.products.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "option_value")
@Entity
@IdClass(OptionValueId.class)
@Getter
@Setter
public class OptionValue {
    @Id
    @Column(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Integer productId;

    @Id
    @Column(name = "option_name", nullable = false, length = 20, insertable = false, updatable = false)
    private String name;

    @Id
    @Column(name = "value", nullable = false, length = 20, insertable = false, updatable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "product_id"),
            @JoinColumn(name = "option_name")
    })
    private Option option;

    @ManyToMany(mappedBy = "optionValues")
    private Set<ProductVariant> productVariants;
}