package ca.bgcengineering.promogearreworked.api.orders.aspects.orderquantityprocessing;

import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
abstract class OrderProcessingWrapper<T, I> {

    @RequiredArgsConstructor
    static abstract class Item<I> {

        private final I item;

        abstract int getQuantity();
        abstract int setQuantity();
        abstract int getVariantId();
        abstract int setVariantId();

    }

    private final T order;

    abstract List<I> getOrderItems();
    abstract void setOrderItems(List<I> items);
    abstract Order.Status getStatus();
    abstract void setStatus(Order.Status status);
    abstract T duplicate();

    T get() {
        return order;
    }

}
