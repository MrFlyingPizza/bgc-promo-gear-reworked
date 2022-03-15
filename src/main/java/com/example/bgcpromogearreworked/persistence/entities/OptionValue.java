package com.example.bgcpromogearreworked.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "option_value")
@Entity
@Getter
@Setter
public class OptionValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "value", nullable = false, length = 20)
    private String value;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;

}