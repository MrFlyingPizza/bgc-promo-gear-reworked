package com.example.bgcpromogearreworked.api.users.user.secured.dto;

import com.example.bgcpromogearreworked.persistence.entities.User;
import com.example.bgcpromogearreworked.persistence.repositories.OfficeLocationRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SecuredUserMapper {

    @Autowired
    private OfficeLocationRepository locationRepo;

    @Mapping(source = "locationId", target = "location.id")
    @Mapping(target = "oid", ignore = true)
    @Mapping(target = "displayName", ignore = true)
    public abstract User fromUpdate(SecuredUserUpdate userUpdate, @MappingTarget User user);

    @Mapping(source = "locationId", target = "location.id")
    @Mapping(target = "oid", ignore = true)
    @Mapping(target = "displayName", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract User fromPartialUpdate(SecuredUserPartialUpdate userPartialUpdate, @MappingTarget User user);

    public abstract SecuredUserResponse toResponse(User user);

    public SecuredUserBatchResponse toBatchResponse(Page<User> users) {
        return new SecuredUserBatchResponse(users.getContent().stream().map(this::toResponse).collect(Collectors.toList()),
                users.getTotalPages(),
                users.getNumber(),
                users.isFirst(),
                users.isLast(),
                users.getNumberOfElements(),
                users.getTotalElements(),
                users.getSort().isSorted());
    }

    @AfterMapping
    protected void mapOfficeLocationPartialUpdateZeroAsNull(SecuredUserPartialUpdate userPartialUpdate, @MappingTarget User user) {
        if (userPartialUpdate.getLocationId() == 0) {
            user.setLocation(null);
        }
    }

    @AfterMapping
    protected void mapOfficeLocationFromRepoOrNull(@MappingTarget User user) {
        if (user.getLocation() == null) {
            return;
        }
        Long officeId = user.getLocation().getId();
        if (officeId == null) {
            user.setLocation(null);
        } else {
            user.setLocation(locationRepo.getById(officeId));
        }
    }

}
