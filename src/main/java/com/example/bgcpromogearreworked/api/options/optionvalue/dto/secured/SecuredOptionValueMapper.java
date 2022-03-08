package com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured;

import com.example.bgcpromogearreworked.api.options.option.dto.secured.SecuredOptionBatchResponse;
import com.example.bgcpromogearreworked.api.options.persistence.OptionValue;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.util.Streamable;

@Mapper(componentModel = "spring")
public abstract class SecuredOptionValueMapper {

    public abstract OptionValue fromCreate(SecuredOptionValueCreate optionValueCreate);

    public abstract OptionValue fromUpdate(SecuredOptionValueUpdate optionValueUpdate, @MappingTarget OptionValue optionValue);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract OptionValue fromPartialUpdate(SecuredOptionValuePartialUpdate optionValuePartialUpdate, @MappingTarget OptionValue optionValue);

    public abstract SecuredOptionValueResponse toResponse(OptionValue optionValue);

    public SecuredOptionValueBatchResponse toBatchResponse(Iterable<OptionValue> optionValues) {
        return new SecuredOptionValueBatchResponse(Streamable.of(optionValues).map(OptionValue::getValue).toList());
    }

}
