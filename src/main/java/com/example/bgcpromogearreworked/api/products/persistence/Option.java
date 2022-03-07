package com.example.bgcpromogearreworked.api.products.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "option")
@Entity
@Getter
@Setter
public class Option {

    @Id
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "option")
    private Set<OptionValue> values;

    @ManyToMany(mappedBy = "options")
    private Set<Product> products;
}