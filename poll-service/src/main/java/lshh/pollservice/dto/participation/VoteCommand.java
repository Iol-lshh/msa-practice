package lshh.pollservice.dto.participation;

import lshh.pollservice.dto.InputDto;

import java.util.List;

public record VoteCommand(
    Long userId,
    Long pollId,
    List<Long> optionIds
)  implements InputDto {
}
