package com.example.bgcpromogearreworked.api.options.option.dto.secured;

import com.example.bgcpromogearreworked.api.options.persistence.Option;
import com.example.bgcpromogearreworked.api.options.persistence.OptionValue;
import org.mapstruct.*;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class SecuredOptionMapper {

    public abstract Option fromCreate(SecuredOptionCreate optionCreate);

    public abstract Option fromUpdate(SecuredOptionUpdate optionUpdate, @MappingTarget Option option);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Option fromPartialUpdate(SecuredOptionPartialUpdate optionPartialUpdate, @MappingTarget Option option);

    public abstract SecuredOptionResponse toResponse(Option option);

    @Mapping(source = "value", target = ".")
    protected abstract String map(OptionValue optionValue);

    public SecuredOptionBatchResponse toBatchResponse(Iterable<Option> options) {
        return new SecuredOptionBatchResponse(Streamable.of(options).map(this::toResponse).toList());
    }

}
