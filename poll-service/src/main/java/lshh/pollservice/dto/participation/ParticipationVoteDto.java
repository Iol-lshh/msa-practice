package lshh.pollservice.dto.participation;

import lshh.pollservice.domain.entity.participation.ParticipationScheduleOption;
import lshh.pollservice.dto.OutputDto;

import java.time.Instant;

public record ParticipationVoteDto(
        Long id,
        Long pollOptionId,
        ParticipationState state,
        Instant createdAt,
        Long participationId
)  implements OutputDto {
    public static ParticipationVoteDto from(ParticipationScheduleOption vote) {
        return new ParticipationVoteDto(vote.getId(), vote.getPollOptionId(), vote.getState(), vote.getCreatedAt(), vote.getParticipation().getId());
    }
}
