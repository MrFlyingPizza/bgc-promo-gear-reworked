package ca.bgcengineering.promogearreworked.api.orders.aspects.orderpartitioning;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
abstract class ProcessableItem<T> {

    private final T item;

    abstract Integer getQuantity();
    abstract Long getVariantId();
    abstract ProcessableItem<T> partition(int quantity);

    T get() {
        return item;
    }

}
