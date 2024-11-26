package lshh.pollservice.domain.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

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
    Boolean logOuted;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    public boolean isNeedRefresh() {
        // todo
        return false;
    }

    public boolean isExpired(Clock clock) {
        return expiredAt.isBefore(clock.instant()) || logOuted;
    }

    public void logOut() {
        this.logOuted = true;
    }
}
