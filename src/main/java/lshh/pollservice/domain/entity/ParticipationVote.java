package lshh.pollservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lshh.pollservice.dto.participation.ParticipationState;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ParticipationVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long pollOptionId;
    @Enumerated(EnumType.STRING)
    ParticipationState state;
    Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    Participation participation;
}
