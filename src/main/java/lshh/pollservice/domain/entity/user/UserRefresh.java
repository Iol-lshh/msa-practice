package lshh.pollservice.domain.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    String refresh;
    String loginId;
    LocalDateTime createdAt;
    LocalDateTime expiredAt;
    Boolean logOut;

    public boolean isNeedRefresh() {
        // todo
        return false;
    }

    public boolean isExpired() {
        return expiredAt.isBefore(LocalDateTime.now()) || logOut;
    }
}
