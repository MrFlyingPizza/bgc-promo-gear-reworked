package ca.bgcengineering.promogearreworked.api.users.user;

import ca.bgcengineering.promogearreworked.persistence.entities.User;
import ca.bgcengineering.promogearreworked.api.users.exceptions.UserNotFoundException;
import ca.bgcengineering.promogearreworked.persistence.repositories.UserRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    public boolean checkUserExists(UUID oid) {
        return userRepo.existsByOid(oid);
    }

    public boolean checkUserExists(Long userId) {
        return userRepo.existsById(userId);
    }

    public User getUser(Long userId) {
        assert userId != null;
        return userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User getUser(UUID oid) {
        assert oid != null;
        return userRepo.findByOid(oid).orElseThrow(UserNotFoundException::new);
    }

    public Page<User> getUsers(Predicate predicate, Pageable pageable) {
        assert predicate != null && pageable != null;
        return userRepo.findAll(predicate, pageable);
    }

    public <T> User updateUser(Long userId, T source, BiFunction<T, User, User> mapper) {
        assert source != null && mapper != null;
        User originalUser = userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
        UUID originalOid = originalUser.getOid();
        User updatedUser = mapper.apply(source, originalUser);
        assert updatedUser.getOid().equals(originalOid) && updatedUser.getId().equals(userId);
        return userRepo.saveAndFlush(updatedUser);
    }

    public <T> User updateUser(UUID oid, T source, BiFunction<T, User, User> mapper) {
        assert source != null && mapper != null;
        User originalUser = userRepo.findByOid(oid).orElseThrow(UserNotFoundException::new);
        Long originalId = originalUser.getId();
        User updatedUser = mapper.apply(source, originalUser);
        assert updatedUser.getId().equals(originalId) && updatedUser.getOid().equals(oid);
        return userRepo.saveAndFlush(updatedUser);
    }
}
