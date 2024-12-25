package lshh.pollservice.domain.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lshh.auth.lib.util.HashTokenHelper;
import lshh.auth.lib.type.UserRoleAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Clock;
import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class UserMember implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String loginId;
    String name;
    String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<UserAuthority> userAuthorities;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<UserRefresh> userRefresh;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<UserAuthentication> userAuthentication;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userAuthorities;
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
                .build();
        this.userRefresh.add(result);
        return result;
    }

    public UserRefresh logOut(String refresh, Clock clock) {
        var optional = this.userRefresh.stream()
                .filter(userRefresh -> userRefresh.getToken().equals(refresh))
                .findFirst();
        optional.ifPresent(userRefresh -> userRefresh.logOut(clock));
        return optional.orElse(null);
    }

    public boolean isNeedRefresh(String refresh) {
        return this.userRefresh.stream()
                .noneMatch(userRefresh ->
                        userRefresh.getToken().equals(refresh)
                        && !userRefresh.isNeedRefresh()
                );
    }

    public void addRole(UserRoleAuthority role) {
        if(this.userAuthorities.stream().anyMatch(userAuthority -> userAuthority.getRole().equals(role))){
            return;
        }
        UserAuthority userAuthority = UserAuthority.builder()
                .role(role)
                .build();
        this.userAuthorities.add(userAuthority);
    }

    public void removeRole(UserRoleAuthority role) {
        this.userAuthorities.removeIf(userAuthority -> userAuthority.getRole().equals(role));
    }
}
