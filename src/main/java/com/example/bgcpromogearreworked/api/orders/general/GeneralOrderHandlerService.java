package com.example.bgcpromogearreworked.api.orders.general;

import com.example.bgcpromogearreworked.api.orders.OrderService;
import com.example.bgcpromogearreworked.api.orders.general.dto.GeneralOrderCreate;
import com.example.bgcpromogearreworked.api.orders.general.dto.GeneralOrderMapper;
import com.example.bgcpromogearreworked.persistence.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class GeneralOrderHandlerService {

    // TODO: 2022-03-27 finish implement
    private final OrderService service;
    private final GeneralOrderMapper mapper;
}
