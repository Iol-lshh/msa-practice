package lshh.pollservice.dto.participation;

import lshh.pollservice.domain.entity.participation.Participation;
import lshh.pollservice.dto.Result;

import java.util.List;

public record ParticipationDetail(
        Long id,
        Long userId,
        Long pollId,
        List<ParticipationVoteDto> voted
)  implements Result {
    public static ParticipationDetail from(Participation participation){
        return new ParticipationDetail(participation.getId(), participation.getUserId(), participation.getPollId(), participation.getVoteList().stream().map(ParticipationVoteDto::from).toList());
    }
}
