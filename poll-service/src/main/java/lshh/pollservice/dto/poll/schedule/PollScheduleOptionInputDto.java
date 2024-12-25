package lshh.pollservice.dto.poll.schedule;

import lshh.pollservice.dto.InputDto;

public record PollScheduleOptionInputDto(
        Long scheduleId
) implements InputDto {
}
