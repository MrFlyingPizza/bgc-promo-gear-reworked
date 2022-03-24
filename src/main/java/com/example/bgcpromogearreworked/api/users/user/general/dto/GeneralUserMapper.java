package com.example.bgcpromogearreworked.api.users.user.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class GeneralUserMapper {

    public abstract GeneralUserResponse toResponse(User user);

}
