package lshh.pollservice.domain.entity.participation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lshh.pollservice.dto.participation.ParticipationState;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userId;
    Long pollId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ParticipationScheduleOption> voteList;

    public void vote(List<Long> pollOptionIds, Clock clock){
        this.voteList.clear();
        var list = pollOptionIds.stream()
                .map(pollOptionId -> ParticipationScheduleOption.builder()
                        .participation(this)
                        .pollOptionId(pollOptionId)
                        .state(ParticipationState.VOTED)
                        .createdAt(Instant.now(clock))
                        .build())
                .toList();
        this.voteList.addAll(list);
    }
}
