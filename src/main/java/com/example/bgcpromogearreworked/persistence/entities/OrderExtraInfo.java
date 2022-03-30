package com.example.bgcpromogearreworked.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Table(name = "order_extra_info")
@Entity
@Getter
@Setter
public class OrderExtraInfo {
    @Id
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "recipient_info", nullable = false, length = 256)
    private String recipientInfo;

    @Column(name = "required_date", nullable = false)
    private Instant requiredDate;
}