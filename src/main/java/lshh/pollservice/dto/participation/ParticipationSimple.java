package lshh.pollservice.dto.participation;

import lshh.pollservice.domain.entity.Participation;

public record ParticipationSimple(
        Long id,
        Long userId,
        Long pollId
) {
    public static ParticipationSimple from(Participation participation) {
        return new ParticipationSimple(participation.getId(), participation.getUserId(), participation.getPollId());
    }
}
