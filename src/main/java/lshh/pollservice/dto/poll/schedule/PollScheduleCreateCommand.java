package lshh.pollservice.dto.poll.schedule;

import lshh.pollservice.dto.poll.property.PollOptionType;
import lshh.pollservice.dto.poll.property.PollState;

import java.util.List;

public record PollScheduleCreateCommand(
        String title,
        String description,
        PollState state,
        PollOptionType type,
        List<PollScheduleOptionRequest> options
) {

}
