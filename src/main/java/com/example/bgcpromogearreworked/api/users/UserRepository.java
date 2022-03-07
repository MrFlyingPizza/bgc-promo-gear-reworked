package com.example.bgcpromogearreworked.api.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByOid(UUID oid);
}
