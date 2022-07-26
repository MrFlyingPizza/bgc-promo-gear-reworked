package ca.bgcengineering.promogearreworked.persistence.entities;

import ca.bgcengineering.promogearreworked.persistence.auditing.Auditable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "transfer")
public class Transfer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH}, optional = false)
    @JoinColumns({
            @JoinColumn(name = "origin_id", referencedColumnName = "location_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "variant_id", referencedColumnName = "variant_id", nullable = false, insertable = false, updatable = false)
    })
    private InventoryLevel originInventoryLevel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH}, optional = false)
    @JoinColumns({
            @JoinColumn(name = "destination_id", referencedColumnName = "location_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "variant_id", referencedColumnName = "variant_id", nullable = false, insertable = false, updatable = false)
    })
    private InventoryLevel destinationInventoryLevel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "origin_id", nullable = false)
    private OfficeLocation origin;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "destination_id", nullable = false)
    private OfficeLocation destination;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "variant_id", nullable = false)
    private ProductVariant variant;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Lob
    @Type(type = "org.hibernate.type.StringType")
    @Column(name = "comments")
    private String comments;

}