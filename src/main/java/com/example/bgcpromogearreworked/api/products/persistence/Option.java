package com.example.bgcpromogearreworked.api.products.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "option")
@Entity
@IdClass(OptionId.class)
@Getter
@Setter
public class Option {
    @Id
    @Column(name = "product_id")
    private Integer productId;

    @Id
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @OneToMany(mappedBy = "option")
    private Set<OptionValue> values;
}