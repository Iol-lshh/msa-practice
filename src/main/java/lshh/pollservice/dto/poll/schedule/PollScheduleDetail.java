package lshh.pollservice.dto.poll.schedule;

import lshh.pollservice.domain.entity.Poll;
import lshh.pollservice.dto.poll.property.PollState;

import java.util.List;

public record PollScheduleDetail(
        Long id,
        String title,
        String description,
        PollState state,
        List<PollScheduleOptionDto> options
) {
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
