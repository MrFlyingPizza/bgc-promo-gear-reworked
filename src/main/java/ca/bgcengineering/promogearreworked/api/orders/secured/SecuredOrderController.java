package ca.bgcengineering.promogearreworked.api.orders.secured;

import ca.bgcengineering.promogearreworked.api.orders.secured.dto.SecuredOrderCreate;
import ca.bgcengineering.promogearreworked.api.orders.secured.dto.SecuredOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/secured/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredOrderController {

    private final SecuredOrderHandlerService handlerService;

    @PostMapping
    private SecuredOrderResponse createOrder(SecuredOrderCreate orderCreate) {
        return null;
    }

}
