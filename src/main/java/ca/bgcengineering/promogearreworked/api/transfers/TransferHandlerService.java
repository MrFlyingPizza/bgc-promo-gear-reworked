package ca.bgcengineering.promogearreworked.api.transfers;

import ca.bgcengineering.promogearreworked.api.transfers.dto.TransferCreate;
import ca.bgcengineering.promogearreworked.api.transfers.dto.TransferMapper;
import ca.bgcengineering.promogearreworked.persistence.entities.Transfer;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class TransferHandlerService {
    private final TransferService service;
    private final TransferMapper mapper;

    public TransferHandlerService(TransferService service, TransferMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    Transfer handleTransferCreate(@Valid TransferCreate transferCreate) {
        return service.createTransfer(transferCreate, mapper::fromCreate);
    }

    Transfer handleTransferGet(long id) {
        return service.getTransfer(id);
    }

    Page<Transfer> handleTransferBatchGet(Predicate predicate, Pageable pageable) {
        return service.getTransfers(predicate, pageable);
    }

    public TransferService service() {
        return service;
    }

    public TransferMapper mapper() {
        return mapper;
    }
}
