package lshh.pollservice.dto.schedule;

import lshh.core.lib.type.InputDto;

import java.time.Instant;

public record ScheduleUpdate(
        Long id,
        Instant startAt,
        Instant endAt,
        ScheduleState state
) implements InputDto {
}
