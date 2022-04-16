package ca.bgcengineering.promogearreworked.api.options.optionvalue.secured.dto;

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

    @Mapping(source = "optionId", target = "option.id")
    @Mapping(target = "id", ignore = true)
    public abstract OptionValue fromCreate(SecuredOptionValueCreate optionValueCreate);

    @Mapping(source = "optionId", target = "option.id")
    public abstract OptionValue fromUpdate(SecuredOptionValueUpdate optionValueUpdate, @MappingTarget OptionValue optionValue);

    @Mapping(source = "optionId", target = "option.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract OptionValue fromPartialUpdate(SecuredOptionValuePartialUpdate optionValuePartialUpdate, @MappingTarget OptionValue optionValue);

    public abstract SecuredOptionValueResponse toResponse(OptionValue optionValue);

    public SecuredOptionValueBatchResponse toBatchResponse(List<OptionValue> values) {
        return new SecuredOptionValueBatchResponse(values.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @AfterMapping
    protected void setOptionNullIfNoOptionId(@MappingTarget OptionValue optionValue) {
        if (optionValue.getOption() == null) {
            return;
        }
        Long optionId = optionValue.getOption().getId();
        if (optionId == null) {
            optionValue.setOption(null);
        } else {
            optionValue.setOption(optionRepo.getById(optionId));
        }
    }

}
