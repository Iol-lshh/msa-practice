package lshh.pollservice.dto.schedule;

import lshh.pollservice.domain.entity.schedule.Schedule;
import lshh.pollservice.dto.Result;

import java.time.Instant;

public record ScheduleSimple(
        Long id,
        Instant startAt,
        Instant endAt,
        ScheduleState state
) implements Result {
    public static ScheduleSimple from(Schedule schedule) {
        return new ScheduleSimple(
                schedule.getId(),
                schedule.getStartAt(),
                schedule.getEndAt(),
                schedule.getState()
        );
    }
}
