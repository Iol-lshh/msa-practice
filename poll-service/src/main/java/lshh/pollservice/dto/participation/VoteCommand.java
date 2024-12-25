package lshh.pollservice.dto.participation;

import lshh.core.lib.type.InputDto;

import java.util.List;

public record VoteCommand(
    Long userId,
    Long pollId,
    List<Long> optionIds
)  implements InputDto {
}
