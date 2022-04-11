package ca.bgcengineering.promogearreworked.api.users.user.secured.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.User;
import ca.bgcengineering.promogearreworked.persistence.entities.OfficeLocation;
import ca.bgcengineering.promogearreworked.persistence.repositories.OfficeLocationRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SecuredUserMapper {

    @Autowired
    private OfficeLocationRepository locationRepo;

    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "oid", ignore = true)
    @Mapping(target = "displayName", ignore = true)
    public abstract User fromUpdate(SecuredUserUpdate userUpdate, @MappingTarget User user);

    @Mapping(source = "locationId", target = "location")
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

    protected OfficeLocation mapOfficeLocationFromId(Long id) {
        return id == null || id == 0 ? null : locationRepo.getById(id);
    }

}
