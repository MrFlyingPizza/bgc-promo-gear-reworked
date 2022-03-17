package com.example.bgcpromogearreworked.persistence.entities;

import com.example.bgcpromogearreworked.persistence.auditing.Auditable;
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

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "image_id", insertable = false, updatable = false)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private ProductImage image;

    @Column(name = "wait_list_threshold")
    private Integer waitListThreshold;

    @ManyToMany
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
    private Set<OptionValue> optionValues;



}