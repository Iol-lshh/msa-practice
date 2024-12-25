package lshh.pollservice.infrastructure.jpa;

import lshh.pollservice.domain.entity.auth.google.GoogleAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoogleAuthenticationJpaRepository extends JpaRepository<GoogleAccount, Long> {

}
