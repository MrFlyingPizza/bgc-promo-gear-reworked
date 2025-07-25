package ca.bgcengineering.promogearreworked.persistence.entities;

import com.querydsl.core.annotations.QueryInit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Table(name = "category")
@Entity
@Getter
@Setter
public class Category {

    @Id
    @SequenceGenerator(name = "category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "parent_id")
    @QueryInit("id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> subcategories = new LinkedHashSet<>();
}