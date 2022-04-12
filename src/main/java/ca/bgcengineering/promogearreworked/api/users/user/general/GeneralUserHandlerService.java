package ca.bgcengineering.promogearreworked.api.users.user.general;

import ca.bgcengineering.promogearreworked.persistence.entities.User;
import ca.bgcengineering.promogearreworked.api.users.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeneralUserHandlerService {

    private final UserService service;

    User handleGetUser(UUID oid) {
        return service.getUser(oid);
    }

}
