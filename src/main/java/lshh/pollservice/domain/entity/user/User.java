package lshh.pollservice.domain.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lshh.pollservice.common.lib.HashTokenHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Clock;
import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "users")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String loginId;
    String name;
    String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<UserRefresh> userRefresh;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<UserAuthentication> userAuthentication;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    public UserAuthentication authenticate(HashTokenHelper authenticateTokenHelper, Clock clock) {
        String authenticateToken = authenticateTokenHelper.generateToken(this);
        UserAuthentication result = UserAuthentication.builder()
                .token(authenticateToken)
                .createdAt(clock.instant())
                .expiredAt(clock.instant().plusMillis(authenticateTokenHelper.getExpirationTime()))
                .confirmed(false)
                .build();
        this.userAuthentication.add(result);
        return result;
    }

    public UserRefresh refreshAuthorization(HashTokenHelper refreshTokenHelper, Clock clock) {
        String refreshToken = refreshTokenHelper.generateToken(this);
        UserRefresh result = UserRefresh.builder()
                .token(refreshToken)
                .createdAt(clock.instant())
                .expiredAt(clock.instant().plusMillis(refreshTokenHelper.getExpirationTime()))
                .logOuted(false)
                .build();
        this.userRefresh.add(result);
        return result;
    }

    public UserRefresh logOut(String refresh) {
        var optional = this.userRefresh.stream()
                .filter(userRefresh -> userRefresh.getToken().equals(refresh))
                .findFirst();
        optional.ifPresent(UserRefresh::logOut);
        return optional.orElse(null);
    }

    public boolean isNeedRefresh(String refresh) {
        return this.userRefresh.stream()
                .noneMatch(userRefresh ->
                        userRefresh.getToken().equals(refresh)
                        && !userRefresh.isNeedRefresh()
                );
    }
}
