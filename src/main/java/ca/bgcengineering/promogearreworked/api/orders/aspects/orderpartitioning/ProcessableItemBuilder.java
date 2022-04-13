package ca.bgcengineering.promogearreworked.api.orders.aspects.orderpartitioning;

import ca.bgcengineering.promogearreworked.api.orders.general.dto.GeneralOrderCreate;
import ca.bgcengineering.promogearreworked.api.orders.secured.dto.SecuredOrderCreate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

abstract class ProcessableItemBuilder<T> {

    private static final Map<Class<?>, ProcessableItemBuilder<?>> builders = Map.ofEntries(
            Map.entry(GeneralOrderCreate.NestedOrderItem.class, new ProcessableItemBuilder<GeneralOrderCreate.NestedOrderItem>() {
                @Override
                ProcessableItem<GeneralOrderCreate.NestedOrderItem> construct(GeneralOrderCreate.NestedOrderItem item) {
                    return new ProcessableItem<>(item) {
                        @Override
                        Integer getQuantity() {
                            return item.getQuantity();
                        }

                        @Override
                        Long getVariantId() {
                            return item.getVariantId();
                        }

                        @Override
                        ProcessableItem<GeneralOrderCreate.NestedOrderItem> partition(int quantity) {
                            return construct(item.toBuilder().quantity(quantity).build());
                        }
                    };
                }
            }),
            Map.entry(SecuredOrderCreate.NestedOrderItem.class, new ProcessableItemBuilder<SecuredOrderCreate.NestedOrderItem>() {
                @Override
                ProcessableItem<SecuredOrderCreate.NestedOrderItem> construct(SecuredOrderCreate.NestedOrderItem item) {
                    return new ProcessableItem<>(item) {
                        @Override
                        Integer getQuantity() {
                            return item.getQuantity();
                        }

                        @Override
                        Long getVariantId() {
                            return item.getVariantId();
                        }

                        @Override
                        ProcessableItem<SecuredOrderCreate.NestedOrderItem> partition(int quantity) {
                            return construct(item.toBuilder().quantity(quantity).build());
                        }
                    };
                }
            })
    );

    static <T> ProcessableItemBuilder<T> get(Class<T> itemClass) {
        return (ProcessableItemBuilder<T>) builders.get(itemClass);
    }

    abstract ProcessableItem<T> construct(T item);

    List<ProcessableItem<T>> construct(List<T> items) {
        return items.stream().map(this::construct).collect(Collectors.toList());
    }

    List<T> reduce(List<ProcessableItem<T>> items) {
        return items.stream().map(ProcessableItem::get).collect(Collectors.toList());
    }
}
