package ca.bgcengineering.promogearreworked.api.options.optionvalue.secured.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.Option;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionRepository;
import ca.bgcengineering.promogearreworked.persistence.entities.OptionValue;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SecuredOptionValueMapper {

    @Autowired
    private OptionRepository optionRepo;

    @Mapping(source = "optionId", target = "option")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productVariants", ignore = true)
    public abstract OptionValue fromCreate(SecuredOptionValueCreate optionValueCreate);

    @Mapping(source = "optionId", target = "option")
    @Mapping(target = "productVariants", ignore = true)
    public abstract OptionValue fromUpdate(SecuredOptionValueUpdate optionValueUpdate, @MappingTarget OptionValue optionValue);

    @Mapping(source = "optionId", target = "option")
    @Mapping(target = "productVariants", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract OptionValue fromPartialUpdate(SecuredOptionValuePartialUpdate optionValuePartialUpdate, @MappingTarget OptionValue optionValue);

    @Mapping(source = "option.name", target = "name")
    @Mapping(source = "id", target = "valueId")
    @Mapping(source = "option.id", target = "optionId")
    public abstract SecuredOptionValueResponse toResponse(OptionValue optionValue);

    public SecuredOptionValueBatchResponse toBatchResponse(List<OptionValue> values) {
        return new SecuredOptionValueBatchResponse(values.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    protected Option mapOptionFromId(Long optionId) {
        return optionId == null || optionId == 0 ? null : optionRepo.getById(optionId);
    }

}
