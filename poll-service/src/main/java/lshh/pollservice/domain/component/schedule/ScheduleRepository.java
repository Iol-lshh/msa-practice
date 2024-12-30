package lshh.pollservice.domain.component.schedule;

import lshh.core.lib.component.persistence.EntityRepository;
import lshh.pollservice.domain.entity.schedule.Schedule;

import java.util.List;

public interface ScheduleRepository extends EntityRepository<Schedule, Long> {
    List<Schedule> findAll();

    Schedule save(Schedule schedule);
}
