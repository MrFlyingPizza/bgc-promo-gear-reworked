package ca.bgcengineering.promogearreworked.api.orders.aspects.orderquantityprocessing;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemPartitions<T> {

    final List<T> available = new ArrayList<>();
    final List<T> unavailable = new ArrayList<>();

}
