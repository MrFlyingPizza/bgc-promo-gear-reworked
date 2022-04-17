package ca.bgcengineering.promogearreworked.api.orders;

import ca.bgcengineering.promogearreworked.api.orders.exceptions.InsufficientCreditException;
import ca.bgcengineering.promogearreworked.api.users.user.UserService;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderUserManagerService {

    private final UserService userService;

    protected void deductCredit(final Long userId, final BigDecimal credit) {
        userService.updateUser(userId, credit, (totalCost, user) -> {
            BigDecimal newCredit = user.getCredit().subtract(totalCost);
            if (newCredit.compareTo(BigDecimal.ZERO) < 0) {
                throw new InsufficientCreditException();
            }
            user.setCredit(newCredit);
            return user;
        });
    }

    public void manage(@NonNull Order order) {
        if (order.getStatus() == Order.Status.CANCELLED) {
            return;
        }
        deductCredit(order.getRecipient().getId(), order.getTotalCost());
    }

    public void manage(@NonNull Order currentOrder, @NonNull Order previousOrder) {
        final Order.Status currentStatus = currentOrder.getStatus();
        final Order.Status previousStatus = previousOrder.getStatus();
        final Long userId = currentOrder.getRecipient().getId();
        BigDecimal change;
        if (currentStatus == Order.Status.CANCELLED && previousStatus == Order.Status.CANCELLED) {
            return;
        } else if (currentStatus == Order.Status.CANCELLED) {
            change = currentOrder.getTotalCost().negate();
        } else if (previousStatus == Order.Status.CANCELLED) {
            change = currentOrder.getTotalCost();
        } else {
            change = currentOrder.getTotalCost().subtract(previousOrder.getTotalCost());
        }
        deductCredit(userId, change);
    }

}
