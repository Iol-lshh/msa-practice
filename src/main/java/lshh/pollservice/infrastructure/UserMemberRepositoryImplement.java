package lshh.pollservice.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lshh.pollservice.common.lib.ClockManager;
import lshh.pollservice.domain.component.user.UserMemberRepository;
import lshh.pollservice.domain.entity.user.UserMember;
import lshh.pollservice.infrastructure.jpa.UserJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.util.Optional;

import static lshh.pollservice.domain.entity.user.QUserMember.userMember;

@RequiredArgsConstructor
@Repository
public class UserMemberRepositoryImplement extends AbstractRepositoryWithJpa<UserMember, Long>
        implements UserMemberRepository {
    private final UserJpaRepository jpaRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final ClockManager clockManager;


    @Override
    protected JpaRepository<UserMember, Long> jpaRepository() {
        return this.jpaRepository;
    }

    @Override
    public Optional<UserMember> findByLoginId(String loginId) {
        return jpaRepository.findByLoginId(loginId);
    }

    @Override
    public Optional<UserMember> findByLoginIdAndPassword(String loginId, String password) {
        return jpaRepository.findByLoginIdAndPassword(loginId, password);
    }

    @Override
    public Optional<UserMember> findByRefreshToken(String refresh) {
        Clock clock = clockManager.getClock();
        return Optional.ofNullable(
                jpaQueryFactory
                    .selectFrom(userMember)
                    .where(userMember.userRefresh.any().token.eq(refresh)
                        .and(userMember.userRefresh.any().logOuted.eq(false))
                        .and(userMember.userRefresh.any().expiredAt.after(clock.instant()))
                    )
                    .fetchOne()
        );
    }

    @Override
    public Optional<UserMember> findByAuthenticationToken(String token) {
        Clock clock = clockManager.getClock();
        return Optional.ofNullable(
                jpaQueryFactory
                    .selectFrom(userMember)
                    .where(userMember.userAuthentication.any().token.eq(token)
                        .and(userMember.userAuthentication.any().confirmed.eq(false))
                        .and(userMember.userAuthentication.any().expiredAt.after(clock.instant()))
                    )
                    .fetchOne()
        );
    }
}
