package lshh.pollservice.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lshh.pollservice.common.lib.ClockManager;
import lshh.pollservice.domain.component.user.UserRepository;
import lshh.pollservice.domain.entity.user.User;
import lshh.pollservice.infrastructure.jpa.UserJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.util.Optional;

import static lshh.pollservice.domain.entity.user.QUser.user;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImplement extends AbstractRepositoryWithJpa<User, Long>
        implements UserRepository {
    private final UserJpaRepository jpaRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final ClockManager clockManager;


    @Override
    protected JpaRepository<User, Long> jpaRepository() {
        return this.jpaRepository;
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
        Clock clock = clockManager.getClock();
        return Optional.ofNullable(
                jpaQueryFactory
                    .selectFrom(user)
                    .where(user.userRefresh.any().token.eq(refresh)
                        .and(user.userRefresh.any().logOuted.eq(false))
                        .and(user.userRefresh.any().expiredAt.after(clock.instant()))
                    )
                    .fetchOne()
        );
    }

    @Override
    public Optional<User> findByAuthenticationToken(String token) {
        Clock clock = clockManager.getClock();
        return Optional.ofNullable(
                jpaQueryFactory
                    .selectFrom(user)
                    .where(user.userAuthentication.any().token.eq(token)
                        .and(user.userAuthentication.any().confirmed.eq(false))
                        .and(user.userAuthentication.any().expiredAt.after(clock.instant()))
                    )
                    .fetchOne()
        );
    }
}
