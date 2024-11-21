package lshh.pollservice.dto.schedule;

import java.time.Instant;

public record ScheduleCreateCommand(
        Instant startAt,
        Instant endAt,
        ScheduleState state
) {
}
