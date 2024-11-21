package lshh.pollservice.dto.poll;

import lshh.pollservice.domain.entity.Poll;

public record PollSimple(
        Long id,
        String title,
        String description,
        PollState state
){
    public static PollSimple from(Poll poll) {
        return new PollSimple(
                poll.getId(),
                poll.getTitle(),
                poll.getDescription(),
                poll.getState()
        );
    }
}
