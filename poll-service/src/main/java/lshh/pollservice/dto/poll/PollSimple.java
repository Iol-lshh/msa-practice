package lshh.pollservice.dto.poll;

import lshh.pollservice.domain.entity.poll.Poll;
import lshh.core.lib.type.OutputDto;
import lshh.pollservice.dto.poll.type.PollState;

public record PollSimple(
        Long id,
        String title,
        String description,
        PollState state
) implements OutputDto {
    public static PollSimple from(Poll poll) {
        return new PollSimple(
                poll.getId(),
                poll.getTitle(),
                poll.getDescription(),
                poll.getState()
        );
    }
}
