package ca.bgcengineering.promogearreworked.persistence.entities;

import ca.bgcengineering.promogearreworked.persistence.auditing.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "product_variant")
@Entity
@Getter
@Setter
public class ProductVariant extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "image_id", insertable = false, updatable = false)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private ProductImage image;

    @Column(name = "wait_list_threshold", nullable = false)
    private Integer waitListThreshold;

    @Column(name = "is_in_use", nullable = false)
    private Boolean isInUse;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_variant_has_option_value",
            joinColumns = {
                    @JoinColumn(name = "variant_id", referencedColumnName = "id"),
                    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "value_id", referencedColumnName = "id"),
                    @JoinColumn(name = "option_id", referencedColumnName = "option_id")
            }
    )
    private Set<OptionValue> optionValues = new java.util.LinkedHashSet<>();

    @OneToOne(mappedBy = "variant")
    private GlobalInventoryLevel globalInventoryLevel;

}