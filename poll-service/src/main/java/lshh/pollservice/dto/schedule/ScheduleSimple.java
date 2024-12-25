package lshh.pollservice.dto.schedule;

import lshh.pollservice.domain.entity.schedule.Schedule;
import lshh.core.lib.type.OutputDto;

import java.time.Instant;

public record ScheduleSimple(
        Long id,
        Instant startAt,
        Instant endAt,
        ScheduleState state
) implements OutputDto {
    public static ScheduleSimple from(Schedule schedule) {
        return new ScheduleSimple(
                schedule.getId(),
                schedule.getStartAt(),
                schedule.getEndAt(),
                schedule.getState()
        );
    }
}
