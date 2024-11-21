package lshh.pollservice.dto.participation;

import java.util.List;

public record VoteUpdateCommand(
        Long userId,
        Long pollId,
        List<Long> optionIds
) {
}
