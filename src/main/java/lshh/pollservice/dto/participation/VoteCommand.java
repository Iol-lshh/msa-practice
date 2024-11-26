package lshh.pollservice.dto.participation;

import lshh.pollservice.dto.Request;

import java.util.List;

public record VoteCommand(
    Long userId,
    Long pollId,
    List<Long> optionIds
)  implements Request {
}
