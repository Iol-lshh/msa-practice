package lshh.pollservice.dto.poll.schedule;

import lshh.pollservice.domain.entity.poll.Poll;
import lshh.pollservice.dto.OutputDto;
import lshh.pollservice.dto.poll.type.PollState;

import java.util.List;

public record PollScheduleDetail(
        Long id,
        String title,
        String description,
        PollState state,
        List<PollScheduleOptionDto> options
)  implements OutputDto {
    public static PollScheduleDetail from(Poll poll) {
        return new PollScheduleDetail(
                poll.getId(),
                poll.getTitle(),
                poll.getDescription(),
                poll.getState(),
                poll.getOptions().stream()
                        .map(PollScheduleOptionDto::from)
                        .toList()
        );
    }
}
