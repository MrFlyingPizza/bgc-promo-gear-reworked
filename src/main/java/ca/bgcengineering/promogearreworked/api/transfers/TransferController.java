package ca.bgcengineering.promogearreworked.api.transfers;

import ca.bgcengineering.promogearreworked.api.transfers.dto.TransferCreate;
import ca.bgcengineering.promogearreworked.api.transfers.dto.TransferMapper;
import ca.bgcengineering.promogearreworked.api.transfers.dto.TransferResponse;
import ca.bgcengineering.promogearreworked.persistence.entities.Transfer;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secured/transfers")
public class TransferController {

    private final TransferHandlerService handlerService;
    private final TransferService service;
    private final TransferMapper mapper;

    @PostMapping
    private TransferResponse createTransfer(@RequestBody TransferCreate transferCreate) {
        return mapper.toResponse(handlerService.handleTransferCreate(transferCreate));
    }

    @GetMapping("/{id}")
    private TransferResponse getTransfer(@PathVariable long id) {
        return mapper.toResponse(handlerService.handleTransferGet(id));
    }

    @GetMapping
    private Page<TransferResponse> getTransfers(@QuerydslPredicate(root = Transfer.class)Predicate predicate,
                                                Pageable pageable) {
        return mapper.toPagedResponse(handlerService.handleTransferBatchGet(predicate, pageable));
    }

}
