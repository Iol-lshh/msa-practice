package lshh.pollservice.dto.poll.schedule;

import lshh.core.lib.type.InputDto;

public record PollScheduleOptionInputDto(
        Long scheduleId
) implements InputDto {
}
