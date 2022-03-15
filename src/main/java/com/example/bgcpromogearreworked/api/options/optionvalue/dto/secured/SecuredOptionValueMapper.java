package com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured;

import com.example.bgcpromogearreworked.persistence.entities.Option;
import com.example.bgcpromogearreworked.persistence.repositories.OptionRepository;
import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class SecuredOptionValueMapper {

    @Autowired
    private OptionRepository optionRepo;

    @Mapping(source = "optionId", target = "option")
    @Mapping(target = "id", ignore = true)
    public abstract OptionValue fromCreate(SecuredOptionValueCreate optionValueCreate);

    @Mapping(source = "optionId", target = "option")
    public abstract OptionValue fromUpdate(SecuredOptionValueUpdate optionValueUpdate, @MappingTarget OptionValue optionValue);

    @Mapping(source = "optionId", target = "option")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract OptionValue fromPartialUpdate(SecuredOptionValuePartialUpdate optionValuePartialUpdate, @MappingTarget OptionValue optionValue);

    public abstract SecuredOptionValueResponse toResponse(OptionValue optionValue);

    public SecuredOptionValueBatchResponse toBatchResponse(Iterable<OptionValue> optionValues) {
        return new SecuredOptionValueBatchResponse(Streamable.of(optionValues).map(this::toResponse).toList());
    }

    @AfterMapping
    private void setOptionNullIfNoOptionId(@MappingTarget OptionValue optionValue) {
        if (optionValue.getOption().getId() == null) {
            optionValue.setOption(null);
        }
    }

    protected Option map(Long optionId) {
        return optionRepo.getById(optionId);
    }

}
