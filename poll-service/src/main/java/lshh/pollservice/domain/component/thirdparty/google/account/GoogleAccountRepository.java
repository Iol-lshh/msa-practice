package lshh.pollservice.domain.component.thirdparty.google.account;

import lshh.core.lib.exception.PersistenceNotFoundException;
import lshh.core.lib.component.EntityRepository;
import lshh.pollservice.domain.entity.auth.google.GoogleAccount;

import java.util.Optional;

public interface GoogleAccountRepository extends EntityRepository<GoogleAccount, Long> {
    default GoogleAccount getByGoogleId(String googleId){
        return findByGoogleId(googleId)
                .orElseThrow(()->new PersistenceNotFoundException("GoogleAccount not found"));
    }

    Optional<GoogleAccount> findByGoogleId(String googleId);
}
