package com.example.bgcpromogearreworked.api.options.option.dto.general;

import com.example.bgcpromogearreworked.persistence.entities.Option;
import org.mapstruct.Mapper;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class GeneralOptionMapper {

    public abstract GeneralOptionResponse toResponse(Option option);

    public GeneralOptionBatchResponse toBatchResponse(Iterable<Option> options) {
        return new GeneralOptionBatchResponse(Streamable.of(options).map(this::toResponse).toList());
    }

}
