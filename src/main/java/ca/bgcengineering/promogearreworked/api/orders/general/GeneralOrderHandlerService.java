package ca.bgcengineering.promogearreworked.api.orders.general;

import ca.bgcengineering.promogearreworked.api.orders.OrderService;
import ca.bgcengineering.promogearreworked.api.orders.aspects.orderpartitioning.ItemPartitions;
import ca.bgcengineering.promogearreworked.api.orders.aspects.orderpartitioning.OrderQuantityProcessor;
import ca.bgcengineering.promogearreworked.api.orders.exceptions.InvalidWaitListItemException;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderCreate;
import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderMapper;
import ca.bgcengineering.promogearreworked.api.users.cartitem.CartItemService;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class GeneralOrderHandlerService {

    private final OrderService service;
    private final GeneralOrderMapper mapper;
    private final OrderQuantityProcessor quantityProcessor;
    private final CartItemService cartItemService;
    private final ProductVariantRepository variantRepo;

    private List<GeneralOrderCreate> splitOrder(GeneralOrderCreate orderCreate) {

        ItemPartitions<GeneralOrderCreate.NestedOrderItem> partition = quantityProcessor.partition(orderCreate.getItems(), GeneralOrderCreate.NestedOrderItem.class);

        List<GeneralOrderCreate.NestedOrderItem> submissionItems = partition.getAvailable();
        List<GeneralOrderCreate.NestedOrderItem> waitingItems = partition.getUnavailable();

        // check waitlist items are waitlistable
        for (GeneralOrderCreate.NestedOrderItem item : waitingItems) {
            final Long variantId = item.getVariantId();
            if (!variantRepo.getById(variantId).getProduct().getIsWaitListEnabled()) {
                throw new InvalidWaitListItemException(variantId);
            }
        }

        List<GeneralOrderCreate> orders = new ArrayList<>();
        if (!submissionItems.isEmpty()) {
            orders.add(orderCreate.toBuilder().items(submissionItems).status(Order.Status.SUBMITTED).build());
        }
        if (!waitingItems.isEmpty()) {
            orders.add(orderCreate.toBuilder().items(waitingItems).status(Order.Status.WAIT_LIST).build());
        }
        return orders;
    }

    @Transactional
    List<Order> handleOrderCreate(@Valid GeneralOrderCreate orderCreate) {
        List<Order> orders = splitOrder(orderCreate).stream().map(order -> service.createOrder(order, mapper::fromCreate))
                .collect(Collectors.toList());
        // empty the cart of the submitter
        cartItemService.deleteCartItems(orderCreate.getSubmitterId());
        return orders;
    }

    List<Order> handleOrderBatchGet(Long userId) {
        return service.getOrders(userId);
    }

    Order handleOrderGet(Long userId, Long orderId) {
        return service.getOrder(userId, orderId);
    }

}
