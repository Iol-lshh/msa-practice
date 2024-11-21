package lshh.pollservice.dto.participation;

import java.util.List;

public record VoteCommand(
    Long userId,
    Long pollId,
    List<Long> optionIds
) {
}
