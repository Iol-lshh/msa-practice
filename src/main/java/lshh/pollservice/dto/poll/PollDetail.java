package lshh.pollservice.dto.poll;

import lshh.pollservice.domain.entity.Poll;

import java.util.List;

public record PollDetail(
        Long id,
        String title,
        String description,
        PollState state,
        List<PollOptionDto> options
) {
    public static PollDetail from(Poll poll) {
        return new PollDetail(
                poll.getId(),
                poll.getTitle(),
                poll.getDescription(),
                poll.getState(),
                poll.getOptions().stream()
                        .map(PollOptionDto::from)
                        .toList()
        );
    }
}
