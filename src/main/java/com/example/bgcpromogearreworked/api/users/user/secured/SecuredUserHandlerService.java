package com.example.bgcpromogearreworked.api.users.user.secured;

import com.example.bgcpromogearreworked.api.users.user.UserService;
import com.example.bgcpromogearreworked.api.users.user.secured.dto.SecuredUserMapper;
import com.example.bgcpromogearreworked.api.users.user.secured.dto.SecuredUserUpdate;
import com.example.bgcpromogearreworked.persistence.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class SecuredUserHandlerService {

    private final UserService service;
    private final SecuredUserMapper mapper;

    User handleUserGet(Long userId) {
        return service.getUser(userId);
    };

    User handleUserUpdate(SecuredUserUpdate userUpdate) {
        return service.updateUser(userUpdate.getId(), userUpdate, mapper::fromUpdate);
    }
// TODO: 2022-03-24 finish implement
}
