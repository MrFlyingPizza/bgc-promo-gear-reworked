package com.example.bgcpromogearreworked.api.users.user.general;

import com.example.bgcpromogearreworked.api.users.user.UserService;
import com.example.bgcpromogearreworked.persistence.entities.User;
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
