package lshh.pollservice.dto.participation;

import lshh.pollservice.domain.entity.ParticipationScheduleOption;

import java.time.Instant;

public record ParticipationVoteDto(
        Long id,
        Long pollOptionId,
        ParticipationState state,
        Instant createdAt,
        Long participationId
) {
    public static ParticipationVoteDto from(ParticipationScheduleOption vote) {
        return new ParticipationVoteDto(vote.getId(), vote.getPollOptionId(), vote.getState(), vote.getCreatedAt(), vote.getParticipation().getId());
    }
}
