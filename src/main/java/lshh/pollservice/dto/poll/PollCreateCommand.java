package lshh.pollservice.dto.poll;

import java.util.List;

public record PollCreateCommand(
        String title,
        String description,
        PollState state,
        PollType type,
        List<PollOptionRequest> options
) {

}
