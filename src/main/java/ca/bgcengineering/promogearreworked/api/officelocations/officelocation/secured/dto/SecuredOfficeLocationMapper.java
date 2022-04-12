package ca.bgcengineering.promogearreworked.api.officelocations.officelocation.secured.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.OfficeLocation;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SecuredOfficeLocationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    public abstract OfficeLocation fromCreate(SecuredOfficeLocationCreate officeLocationCreate);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    public abstract OfficeLocation fromUpdate(SecuredOfficeLocationUpdate officeLocationUpdate,
                                              @MappingTarget OfficeLocation officeLocation);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract OfficeLocation fromPartialUpdate(SecuredOfficeLocationPartialUpdate officeLocationPartialUpdate,
                                                     @MappingTarget OfficeLocation officeLocation);

    public abstract SecuredOfficeLocationResponse toResponse(OfficeLocation officeLocation);

    public SecuredOfficeLocationBatchResponse toBatchResponse(Page<OfficeLocation> page) {
        return new SecuredOfficeLocationBatchResponse(page.getContent().stream().map(this::toResponse).collect(Collectors.toList()),
                page.getTotalPages(),
                page.getNumber(),
                page.isFirst(),
                page.isLast(),
                page.getNumberOfElements(),
                page.getTotalElements(),
                page.getSort().isSorted());
    }
}
