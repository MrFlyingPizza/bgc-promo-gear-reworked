package ca.bgcengineering.promogearreworked.api.users.user.secured;

import ca.bgcengineering.promogearreworked.api.users.user.secured.dto.SecuredUserMapper;
import ca.bgcengineering.promogearreworked.api.users.user.secured.dto.SecuredUserPartialUpdate;
import ca.bgcengineering.promogearreworked.api.users.user.secured.dto.SecuredUserUpdate;
import ca.bgcengineering.promogearreworked.persistence.entities.User;
import ca.bgcengineering.promogearreworked.api.users.user.UserService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

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
