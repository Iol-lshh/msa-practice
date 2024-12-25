package lshh.pollservice.infrastructure;

import lombok.RequiredArgsConstructor;
import lshh.pollservice.domain.component.thirdparty.google.account.GoogleAccountRepository;
import lshh.pollservice.domain.entity.auth.google.GoogleAccount;
import lshh.pollservice.infrastructure.jpa.GoogleAuthenticationJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class GoogleAccountRepositoryImplement extends AbstractRepositoryWithJpa<GoogleAccount, Long>
    implements GoogleAccountRepository {
    private final GoogleAuthenticationJpaRepository googleAuthenticationJpaRepository;

    @Override
    protected JpaRepository<GoogleAccount, Long> jpaRepository() {
        return this.googleAuthenticationJpaRepository;
    }

    @Override
    public Optional<GoogleAccount> findByGoogleId(String googleId) {
        return Optional.empty();
    }
}
