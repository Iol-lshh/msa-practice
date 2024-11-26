package lshh.pollservice.domain.component.user;

import lshh.pollservice.common.exception.PersistenceNotFoundException;
import lshh.pollservice.domain.component.EntityRepository;
import lshh.pollservice.domain.entity.user.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends EntityRepository<User, Long> {
    default UserDetails getUserDetailsByLoginId(String loginId){
        return findByLoginId(loginId)
                .orElseThrow(()->new PersistenceNotFoundException("User not found"));
    }

    Optional<User> findByLoginId(String loginId);

    default User getByLoginIdAndPassword(String loginId, String password){
        return findByLoginIdAndPassword(loginId, password)
                .orElseThrow(()->new PersistenceNotFoundException("User not found"));
    }

    Optional<User> findByLoginIdAndPassword(String loginId, String password);

    default User getByRefreshToken(String refresh) {
        return findByRefreshToken(refresh)
                .orElseThrow(()->new PersistenceNotFoundException("User not found"));
    }

    Optional<User> findByRefreshToken(String refresh);


    default User getByAuthenticationToken(String token) {
        return findByAuthenticationToken(token)
                .orElseThrow(()->new PersistenceNotFoundException("User not found"));
    }

    Optional<User> findByAuthenticationToken(String token);

    default User getByLoginId(String generateAuthenticationSet){
        return findByLoginId(generateAuthenticationSet)
                .orElseThrow(()->new PersistenceNotFoundException("User not found"));
    }
}
