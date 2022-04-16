package com.example.bgcpromogearreworked.api.orders.secured;

import com.example.bgcpromogearreworked.api.orders.OrderService;
import com.example.bgcpromogearreworked.persistence.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class SecuredOrderHandlerService {

    private final OrderService service;

    Order handleOrderUpdate() {
        return null;
    }

}
