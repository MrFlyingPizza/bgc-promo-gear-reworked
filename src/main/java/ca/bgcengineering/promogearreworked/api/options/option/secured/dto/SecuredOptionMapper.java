package ca.bgcengineering.promogearreworked.api.options.option.secured.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.Option;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

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

    public SecuredOptionBatchResponse toBatchResponse(List<Option> options) {
        return new SecuredOptionBatchResponse(options.stream().map(this::toResponse).collect(Collectors.toList()));
    }

}
