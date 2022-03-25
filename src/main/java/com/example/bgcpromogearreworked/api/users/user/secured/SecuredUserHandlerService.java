package com.example.bgcpromogearreworked.api.users.user.secured;

import com.example.bgcpromogearreworked.api.users.user.UserService;
import com.example.bgcpromogearreworked.api.users.user.secured.dto.SecuredUserMapper;
import com.example.bgcpromogearreworked.api.users.user.secured.dto.SecuredUserPartialUpdate;
import com.example.bgcpromogearreworked.api.users.user.secured.dto.SecuredUserUpdate;
import com.example.bgcpromogearreworked.persistence.entities.User;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class SecuredUserHandlerService {

    private final UserService service;
    private final SecuredUserMapper mapper;

    User handleUserGet(Long userId) {
        return service.getUser(userId);
    }

    User handleUserUpdate(@Valid SecuredUserUpdate userUpdate) {
        return service.updateUser(userUpdate.getId(), userUpdate, mapper::fromUpdate);
    }

    User handleUserPartialUpdate(@Valid SecuredUserPartialUpdate userPartialUpdate) {
        return service.updateUser(userPartialUpdate.getId(), userPartialUpdate, mapper::fromPartialUpdate);
    }

    Page<User> handleUserBatchGet(Predicate predicate, Pageable pageable) {
        return service.getUsers(predicate, pageable);
    }
}
