package ca.bgcengineering.promogearreworked.api.users.user.general.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class GeneralUserMapper {

    public abstract GeneralUserResponse toResponse(User user);

}
