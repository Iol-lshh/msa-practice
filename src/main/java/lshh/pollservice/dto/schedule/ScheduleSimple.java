package lshh.pollservice.dto.schedule;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lshh.pollservice.domain.entity.Schedule;

import java.time.Instant;

public record ScheduleSimple(
        Long id,
        Instant startAt,
        Instant endAt,
        ScheduleState state
) {
    public static ScheduleSimple from(Schedule schedule) {
        return new ScheduleSimple(
                schedule.getId(),
                schedule.getStartAt(),
                schedule.getEndAt(),
                schedule.getState()
        );
    }
}
