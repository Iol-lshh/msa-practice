package lshh.pollservice.dto.schedule;

import java.time.Instant;

public record ScheduleUpdateCommand (
        Long id,
        Instant startAt,
        Instant endAt,
        ScheduleState state
){
}
