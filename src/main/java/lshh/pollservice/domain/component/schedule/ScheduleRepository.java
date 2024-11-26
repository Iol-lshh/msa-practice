package lshh.pollservice.domain.component.schedule;

import lshh.pollservice.domain.component.EntityRepository;
import lshh.pollservice.domain.entity.Schedule;

import java.util.List;

public interface ScheduleRepository extends EntityRepository<Schedule, Long> {
    List<Schedule> findAll();

    Schedule save(Schedule schedule);
}
