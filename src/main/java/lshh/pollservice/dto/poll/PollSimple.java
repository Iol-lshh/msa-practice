package lshh.pollservice.dto.poll;

import lshh.pollservice.domain.entity.poll.Poll;
import lshh.pollservice.dto.Result;
import lshh.pollservice.dto.poll.type.PollState;

public record PollSimple(
        Long id,
        String title,
        String description,
        PollState state
) implements Result {
    public static PollSimple from(Poll poll) {
        return new PollSimple(
                poll.getId(),
                poll.getTitle(),
                poll.getDescription(),
                poll.getState()
        );
    }
}
