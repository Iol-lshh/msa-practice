package lshh.pollservice.dto.participation;

import lshh.pollservice.dto.Request;

import java.util.List;

public record VoteUpdateCommand(
        Long userId,
        Long pollId,
        List<Long> optionIds
)  implements Request {
}
