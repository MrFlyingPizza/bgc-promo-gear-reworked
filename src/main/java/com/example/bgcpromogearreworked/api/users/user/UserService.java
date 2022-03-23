package com.example.bgcpromogearreworked.api.users.user;

import com.example.bgcpromogearreworked.api.users.exceptions.UserNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.User;
import com.example.bgcpromogearreworked.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService { // TODO: 2022-03-21 app registration and handle logins and user info

    private final UserRepository userRepo;

    public User getUser(Long userId) {
        assert userId != null;
        return userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User getUser(UUID oid) {
        assert oid != null;
        return userRepo.findByOid(oid).orElseThrow(UserNotFoundException::new);
    }
}
