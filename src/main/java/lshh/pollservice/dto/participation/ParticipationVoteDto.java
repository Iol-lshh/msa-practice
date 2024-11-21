package lshh.pollservice.dto.participation;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lshh.pollservice.domain.entity.ParticipationVote;

import java.time.Instant;
import java.util.List;

public record ParticipationVoteDto(
        Long id,
        Long pollOptionId,
        ParticipationState state,
        Instant createdAt,
        Long participationId
) {
    public static ParticipationVoteDto from(ParticipationVote vote) {
        return new ParticipationVoteDto(vote.getId(), vote.getPollOptionId(), vote.getState(), vote.getCreatedAt(), vote.getParticipation().getId());
    }
}
