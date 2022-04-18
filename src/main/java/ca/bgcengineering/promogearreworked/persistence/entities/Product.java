package ca.bgcengineering.promogearreworked.persistence.entities;

import ca.bgcengineering.promogearreworked.persistence.auditing.Auditable;
import com.querydsl.core.annotations.QueryInit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Table(name = "product")
@Entity
@Getter
@Setter
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "brand", length = 60)
    private String brand;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @QueryInit({"id", "parent"})
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

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "product_has_option",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "option_id", referencedColumnName = "id")
    )
    private Set<Option> options = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "product_variant_has_option_value",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "value_id", referencedColumnName = "id")
    )
    private Set<OptionValue> optionValues = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductVariant> variants = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductImage> images = new LinkedHashSet<>();

}