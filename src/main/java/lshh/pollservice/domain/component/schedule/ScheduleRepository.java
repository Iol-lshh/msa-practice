package lshh.pollservice.domain.component.schedule;

import lshh.pollservice.common.exception.PersistencNotFoundException;
import lshh.pollservice.domain.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    default Schedule getById(Long id){
        return findById(id).orElseThrow(()-> new PersistencNotFoundException("Schedule not found"));
    }
    Optional<Schedule> findById(Long id);

    List<Schedule> findAll();

    Schedule save(Schedule schedule);
}
