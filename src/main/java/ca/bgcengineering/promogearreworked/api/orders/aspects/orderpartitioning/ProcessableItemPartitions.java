package ca.bgcengineering.promogearreworked.api.orders.aspects.orderpartitioning;

class ProcessableItemPartitions<T> extends ItemPartitions<ProcessableItem<T>> {

    ItemPartitions<T> toItemPartitions() {
        ItemPartitions<T> partitions = new ItemPartitions<>();
        available.forEach(item -> partitions.available.add(item.get()));
        unavailable.forEach(item -> partitions.unavailable.add(item.get()));
        return partitions;
    }

}
