package lshh.pollservice.dto.participation;

import lshh.pollservice.domain.entity.participation.Participation;
import lshh.pollservice.dto.OutputDto;

public record ParticipationSimple(
        Long id,
        Long userId,
        Long pollId
)  implements OutputDto {
    public static ParticipationSimple from(Participation participation) {
        return new ParticipationSimple(participation.getId(), participation.getUserId(), participation.getPollId());
    }
}
