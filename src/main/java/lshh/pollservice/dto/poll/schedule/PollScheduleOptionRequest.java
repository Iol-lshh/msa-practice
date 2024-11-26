package lshh.pollservice.dto.poll.schedule;

import lshh.pollservice.dto.Request;

public record PollScheduleOptionRequest(
        Long scheduleId
) implements Request {
}
