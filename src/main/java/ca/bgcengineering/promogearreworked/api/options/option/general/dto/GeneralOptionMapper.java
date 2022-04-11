package ca.bgcengineering.promogearreworked.api.options.option.general.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.Option;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralOptionMapper {

    public abstract GeneralOptionResponse toResponse(Option option);

    public GeneralOptionBatchResponse toBatchResponse(List<Option> options) {
        return new GeneralOptionBatchResponse(options.stream().map(this::toResponse).collect(Collectors.toList()));
    }

}
