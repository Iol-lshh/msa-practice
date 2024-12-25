package lshh.pollservice.infrastructure.jpa;

import lshh.pollservice.domain.entity.user.UserMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserMember, Long> {
    Optional<UserMember> findByLoginId(String loginId);

    Optional<UserMember> findByLoginIdAndPassword(String loginId, String password);
}
