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
public class UserAuthentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String token;
    Instant createdAt;
    Instant expiredAt;
    Boolean confirmed;

    @ManyToOne(fetch = FetchType.LAZY)
    UserMember userMember;

    public boolean isExpired(Clock clock) {
        return expiredAt.isBefore(clock.instant()) || confirmed;
    }
}
