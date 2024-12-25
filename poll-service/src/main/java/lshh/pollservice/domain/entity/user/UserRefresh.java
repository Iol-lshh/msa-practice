package lshh.pollservice.domain.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class UserRefresh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String token;
    Instant createdAt;
    Instant expiredAt;
    Instant logOutedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    UserMember userMember;

    public boolean isNeedRefresh() {
        // todo
        return false;
    }

    public boolean isExpired(Clock clock) {
        return expiredAt.isBefore(clock.instant()) || logOutedAt != null;
    }

    public void logOut(Clock clock) {
        this.logOutedAt = clock.instant();
    }
}
