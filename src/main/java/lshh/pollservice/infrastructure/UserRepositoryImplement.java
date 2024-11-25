package lshh.pollservice.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lshh.pollservice.common.exception.PersistenceNotFoundException;
import lshh.pollservice.domain.component.auth.RefreshWriter;
import lshh.pollservice.domain.component.user.UserRepository;
import lshh.pollservice.domain.entity.user.User;
import lshh.pollservice.domain.entity.user.UserRefresh;
import lshh.pollservice.infrastructure.jpa.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImplement implements UserRepository, RefreshWriter {
    private final UserJpaRepository jpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<User> findByUserId(Long userId) {
        return jpaRepository.findByid(userId);
    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return jpaRepository.findByLoginId(loginId);
    }

    @Override
    public Optional<User> findByLoginIdAndPassword(String loginId, String password) {
        return jpaRepository.findByLoginIdAndPassword(loginId, password);
    }

    @Override
    public Optional<User> findByRefreshToken(String refresh) {
        // todo
        return null;
    }

    @Override
    public UserRefresh write(UserRefresh userRefresh) {
        User user = jpaRepository.findByLoginId(userRefresh.getLoginId())
                .orElseThrow(()->new PersistenceNotFoundException("User not found"));
        user.addRefresh(userRefresh);
        jpaRepository.save(user);
        return userRefresh;
    }
}
