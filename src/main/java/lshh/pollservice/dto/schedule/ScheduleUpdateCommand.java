package lshh.pollservice.dto.schedule;

import lshh.pollservice.dto.Request;

import java.time.Instant;

public record ScheduleUpdateCommand (
        Long id,
        Instant startAt,
        Instant endAt,
        ScheduleState state
) implements Request {
}
