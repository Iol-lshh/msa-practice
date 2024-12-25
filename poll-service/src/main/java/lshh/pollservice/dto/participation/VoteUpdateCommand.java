package lshh.pollservice.dto.participation;

import lshh.pollservice.dto.InputDto;

import java.util.List;

public record VoteUpdateCommand(
        Long userId,
        Long pollId,
        List<Long> optionIds
)  implements InputDto {
}
