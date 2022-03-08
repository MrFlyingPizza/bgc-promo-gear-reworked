package com.example.bgcpromogearreworked.api.options.option.dto.general;

import com.example.bgcpromogearreworked.api.options.persistence.Option;
import com.example.bgcpromogearreworked.api.options.persistence.OptionValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class GeneralOptionMapper {

    @Mapping(source = "values", target = "values")
    public abstract GeneralOptionResponse toResponse(Option option);

    @Mapping(source = "value", target = ".")
    protected abstract String map(OptionValue optionValue);

    public GeneralOptionBatchResponse toBatchResponse(Iterable<Option> options) {
        return new GeneralOptionBatchResponse(Streamable.of(options).map(this::toResponse).toList());
    }

}
