package com.example.bgcpromogearreworked.api.options.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "option_value")
@Entity
@IdClass(OptionValueId.class)
@Getter
@Setter
public class OptionValue {
    @Id
    @Column(name = "option_name", nullable = false, length = 20)
    private String name;

    @Id
    @Column(name = "value", nullable = false, length = 20)
    private String value;

    @ManyToOne
    @JoinColumn(name = "option_name", insertable = false, updatable = false)
    private Option option;
}