package lshh.pollservice.dto.poll.schedule;

import lshh.pollservice.dto.Request;
import lshh.pollservice.dto.poll.type.PollOptionType;
import lshh.pollservice.dto.poll.type.PollState;

import java.util.List;

public record PollScheduleCreateCommand(
        String title,
        String description,
        PollState state,
        PollOptionType type,
        List<PollScheduleOptionRequest> options
)  implements Request {

}
