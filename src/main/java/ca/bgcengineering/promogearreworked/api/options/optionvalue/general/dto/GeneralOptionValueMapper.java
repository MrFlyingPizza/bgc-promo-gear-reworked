package ca.bgcengineering.promogearreworked.api.options.optionvalue.general.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.OptionValue;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralOptionValueMapper {

    public abstract GeneralOptionValueResponse toResponse(OptionValue value);

    public GeneralOptionValueBatchResponse toBatchResponse(List<OptionValue> values) {
        return new GeneralOptionValueBatchResponse(values.stream().map(this::toResponse).collect(Collectors.toList()));
    }

}
