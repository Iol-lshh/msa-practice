package lshh.pollservice.dto.schedule;

import lshh.pollservice.domain.entity.Schedule;

import java.time.Instant;

public record ScheduleDetail(
        Long id,
        Instant startAt,
        Instant endAt,
        ScheduleState state
) {
    public static ScheduleDetail from(Schedule schedule) {
        return new ScheduleDetail(
                schedule.getId(),
                schedule.getStartAt(),
                schedule.getEndAt(),
                schedule.getState()
        );
    }
}
