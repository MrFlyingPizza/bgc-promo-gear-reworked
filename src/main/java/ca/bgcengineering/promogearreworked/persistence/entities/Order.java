package ca.bgcengineering.promogearreworked.persistence.entities;

import ca.bgcengineering.promogearreworked.persistence.auditing.Auditable;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Table(name = "\"order\"")
@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Order extends Auditable {

    public enum Status {
        SUBMITTED((short) 0),
        PROCESSING((short) 1),
        COMPLETED((short) 2),
        WAIT_LIST((short) 3),
        CANCELLED((short) 4);

        Status(short value) {
        }
    }

    public enum Type {
        REGULAR((short) 0),
        CLIENT((short) 1),
        EVENT((short) 2);

        Type(short value) {
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "submitter", nullable = false)
    private User submitter;

    @ManyToOne
    @JoinColumn(name = "fulfiller")
    private User fulfiller;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient", nullable = false)
    private User recipient;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @Builder.Default
    @Column(name = "submitter_comments", nullable = false, length = 500)
    private String submitterComments = "";

    @Builder.Default
    @Column(name = "fulfiller_comments", nullable = false, length = 500)
    private String fulfillerComments = "";

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private OfficeLocation location;

    @Column(name = "completed_date")
    private Instant completedDate;

    @OneToOne(mappedBy = "order")
    private OrderExtraInfo extraInfo;

    @Builder.Default
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items = new LinkedHashSet<>();

    public BigDecimal getTotalCost() {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (OrderItem orderItem : items) {
            totalCost = totalCost.add(orderItem.getCost());
        }
        return totalCost;
    }

}