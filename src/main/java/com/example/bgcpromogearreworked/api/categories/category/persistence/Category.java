package com.example.bgcpromogearreworked.api.categories.category.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "category")
@Entity
@Setter
@Getter
public class Category {
    @Id
    @SequenceGenerator(name = "category_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "category_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "parent_id", insertable = false, updatable = false)
    private Long parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> subCategories;
}