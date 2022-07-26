package ca.bgcengineering.promogearreworked.api.transfers;

import ca.bgcengineering.promogearreworked.api.inventorylevels.exceptions.InventoryLevelNotFoundException;
import ca.bgcengineering.promogearreworked.api.transfers.exceptions.TransferNotFoundException;
import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevelId;
import ca.bgcengineering.promogearreworked.persistence.entities.Transfer;
import ca.bgcengineering.promogearreworked.persistence.repositories.InventoryLevelRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.OfficeLocationRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductVariantRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.TransferRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final InventoryLevelRepository inventoryLevelRepo;
    private final TransferRepository transferRepo;

    public <T> Transfer createTransfer(T source, Function<T, Transfer> mapper) {
        var transfer = mapper.apply(source);

        var origin = transfer.getOriginInventoryLevel();
        var destination = transfer.getDestinationInventoryLevel();
        var quantity = transfer.getQuantity();

        // subtract from origin
        origin.setAvailableQuantity(origin.getAvailableQuantity() - quantity);
        inventoryLevelRepo.save(origin);

        // add to destination
        destination.setAvailableQuantity(destination.getAvailableQuantity() + quantity);
        inventoryLevelRepo.save(destination);

        transferRepo.save(transfer);

        return transfer;
    }

    public Transfer getTransfer(long id) {
        return transferRepo.findById(id).orElseThrow(TransferNotFoundException::new);
    }

    public Page<Transfer> getTransfers(Predicate predicate, Pageable pageable) {
        return transferRepo.findAll(predicate, pageable);
    }

}
