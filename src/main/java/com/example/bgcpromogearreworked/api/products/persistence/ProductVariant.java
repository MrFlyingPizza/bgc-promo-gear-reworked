package com.example.bgcpromogearreworked.api.products.persistence;

import com.example.bgcpromogearreworked.api.common.auditing.Auditable;
import com.example.bgcpromogearreworked.api.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "product_variant")
@Entity
@Getter
@Setter
public class ProductVariant extends Auditable<User> {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private ProductImage image;

    @Column(name = "wait_list_threshold")
    private Integer waitListThreshold;

    @ManyToMany
    @JoinTable(
            name = "product_variant_has_option_value",
            joinColumns = @JoinColumn(name = "variant_id"),
            inverseJoinColumns = {
                @JoinColumn(name = "option_name"),
                @JoinColumn(name = "option_value")
            }
    )
    private Set<OptionValue> optionValues;

}