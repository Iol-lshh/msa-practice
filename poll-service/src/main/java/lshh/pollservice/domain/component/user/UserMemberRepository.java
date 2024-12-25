package lshh.pollservice.domain.component.user;

import lshh.core.lib.exception.PersistenceNotFoundException;
import lshh.core.lib.component.EntityRepository;
import lshh.pollservice.domain.entity.user.UserMember;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserMemberRepository extends EntityRepository<UserMember, Long> {
    default UserDetails getUserDetailsByLoginId(String loginId){
        return findByLoginId(loginId)
                .orElseThrow(()->new PersistenceNotFoundException("UserMember not found"));
    }

    Optional<UserMember> findByLoginId(String loginId);

    default UserMember getByLoginIdAndPassword(String loginId, String password){
        return findByLoginIdAndPassword(loginId, password)
                .orElseThrow(()->new PersistenceNotFoundException("UserMember not found"));
    }

    Optional<UserMember> findByLoginIdAndPassword(String loginId, String password);

    default UserMember getByRefreshToken(String refresh) {
        return findByRefreshToken(refresh)
                .orElseThrow(()->new PersistenceNotFoundException("UserMember not found"));
    }

    Optional<UserMember> findByRefreshToken(String refresh);


    default UserMember getByAuthenticationToken(String token) {
        return findByAuthenticationToken(token)
                .orElseThrow(()->new PersistenceNotFoundException("UserMember not found"));
    }

    Optional<UserMember> findByAuthenticationToken(String token);

    default UserMember getByLoginId(String loginId){
        return findByLoginId(loginId)
                .orElseThrow(()->new PersistenceNotFoundException("UserMember not found"));
    }
}
