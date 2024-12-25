package lshh.pollservice.dto.schedule;

import lshh.pollservice.dto.InputDto;

import java.time.Instant;

public record ScheduleCreate(
        Instant startAt,
        Instant endAt,
        ScheduleState state
)  implements InputDto {
}
