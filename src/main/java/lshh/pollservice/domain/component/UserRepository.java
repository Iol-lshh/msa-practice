package lshh.pollservice.domain.component;

import lshh.pollservice.common.exception.PersistenceNotFoundException;
import lshh.pollservice.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    default User getById(Long userId){
        return findByUserId(userId).orElseThrow(()->new PersistenceNotFoundException("User not found"));
    }
    Optional<User> findByUserId(Long userId);
}
