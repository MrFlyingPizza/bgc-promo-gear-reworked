package com.example.bgcpromogearreworked.api.options.option.dto.secured;

import com.example.bgcpromogearreworked.persistence.entities.Option;
import org.mapstruct.*;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class SecuredOptionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "values", ignore = true)
    @Mapping(target = "products", ignore = true)
    public abstract Option fromCreate(SecuredOptionCreate optionCreate);

    @Mapping(target = "values", ignore = true)
    @Mapping(target = "products", ignore = true)
    public abstract Option fromUpdate(SecuredOptionUpdate optionUpdate, @MappingTarget Option option);

    @Mapping(target = "values", ignore = true)
    @Mapping(target = "products", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Option fromPartialUpdate(SecuredOptionPartialUpdate optionPartialUpdate, @MappingTarget Option option);

    public abstract SecuredOptionResponse toResponse(Option option);

    public SecuredOptionBatchResponse toBatchResponse(Iterable<Option> options) {
        return new SecuredOptionBatchResponse(Streamable.of(options).map(this::toResponse).toList());
    }

}
