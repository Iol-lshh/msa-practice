package lshh.pollservice.dto.participation;

import lshh.pollservice.domain.entity.participation.Participation;
import lshh.pollservice.dto.Result;

public record ParticipationSimple(
        Long id,
        Long userId,
        Long pollId
)  implements Result {
    public static ParticipationSimple from(Participation participation) {
        return new ParticipationSimple(participation.getId(), participation.getUserId(), participation.getPollId());
    }
}
