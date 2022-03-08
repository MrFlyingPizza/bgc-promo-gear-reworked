package com.example.bgcpromogearreworked.api.products.persistence;

import com.example.bgcpromogearreworked.api.categories.persistence.Category;
import com.example.bgcpromogearreworked.api.options.persistence.Option;
import com.example.bgcpromogearreworked.api.options.persistence.OptionValue;
import com.example.bgcpromogearreworked.api.shared.auditing.Auditable;
import com.example.bgcpromogearreworked.api.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Table(name = "product")
@Entity
@Getter
@Setter
public class Product extends Auditable<User> {
    @Id
    @SequenceGenerator(name = "product_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "product_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "brand", length = 60)
    private String brand;

    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
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

    @ManyToMany
    @JoinTable(
            name = "product_has_option",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "option_name")
    )
    private Set<Option> options;

    @ManyToMany
    @JoinTable(
            name = "product_variant_has_option_value",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "option_name"),
                    @JoinColumn(name = "option_value")
            }
    )
    private Set<OptionValue> optionValues;

    @OneToMany(mappedBy = "product")
    private Set<ProductVariant> variants;

    @OneToMany(mappedBy = "product")
    private Set<ProductImage> images;

}