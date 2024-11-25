package lshh.pollservice.infrastructure.jpa;

import lshh.pollservice.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByid(Long id);

    Optional<User> findByLoginId(String loginId);

    Optional<User> findByLoginIdAndPassword(String loginId, String password);
}
