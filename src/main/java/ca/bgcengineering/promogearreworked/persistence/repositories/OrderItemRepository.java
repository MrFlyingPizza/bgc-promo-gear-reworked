package ca.bgcengineering.promogearreworked.persistence.repositories;

import ca.bgcengineering.promogearreworked.persistence.entities.OrderItem;
import ca.bgcengineering.promogearreworked.persistence.entities.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
