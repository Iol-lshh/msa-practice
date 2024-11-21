package lshh.pollservice.infrastructure;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.component.UserRepository;
import lshh.pollservice.domain.entity.User;
import lshh.pollservice.infrastructure.jpa.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImplement implements UserRepository {
    private final UserJpaRepository jpaRepository;

    @Override
    public Optional<User> findByUserId(Long userId) {
        return jpaRepository.findByid(userId);
    }
}
