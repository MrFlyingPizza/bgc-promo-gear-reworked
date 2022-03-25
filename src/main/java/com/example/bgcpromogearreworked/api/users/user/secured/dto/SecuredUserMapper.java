package com.example.bgcpromogearreworked.api.users.user.secured.dto;

import com.example.bgcpromogearreworked.persistence.entities.User;
import com.example.bgcpromogearreworked.persistence.repositories.OfficeLocationRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class SecuredUserMapper {
    // TODO: 2022-03-24 finish implement
    @Autowired
    private OfficeLocationRepository locationRepo;

    @Mapping(source = "officeId", target = "office.id")
    @Mapping(target = "oid", ignore = true)
    @Mapping(target = "displayName", ignore = true)
    public abstract User fromUpdate(SecuredUserUpdate userUpdate, @MappingTarget User user);

    @AfterMapping
    protected void mapOfficeLocationFromRepoOrNull(@MappingTarget User user) {
        if (user.getOffice() == null) {
            return;
        }
        Long officeId = user.getOffice().getId();
        if (officeId == null) {
            user.setOffice(null);
        } else {
            user.setOffice(locationRepo.getById(officeId));
        }
    }

}
