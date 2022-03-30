package com.example.bgcpromogearreworked.api.orders.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Getter
@RequiredArgsConstructor
public class GeneralOrderResponse {

    @Getter
    @RequiredArgsConstructor
    static class NestedOfficeLocation {
        private final Long id;
        private final String name;
    }

    private final Long id;
    private final String submitter;
    private final String fulfiller;
    private final String recipient;
    private final Order.OrderStatus status;
    private final Order.OrderType type;
    private final String submitterComments;
    private final String fulfillerComments;
    private final NestedOfficeLocation office;
    private final Instant completedDate;
// TODO: 2022-03-27 finish implement
}
