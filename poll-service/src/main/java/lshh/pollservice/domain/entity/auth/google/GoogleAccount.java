package lshh.pollservice.domain.entity.auth.google;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lshh.auth.lib.type.ThirdPartyAccount;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class GoogleAccount implements ThirdPartyAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userId;
    String googleId;
}
